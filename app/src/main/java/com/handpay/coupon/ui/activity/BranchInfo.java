package com.handpay.coupon.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.base.BaseKey;
import com.handpay.coupon.bean.GetPoiBackBean;
import com.handpay.coupon.bean.ProvincEBean;
import com.handpay.coupon.databinding.ActivityBranchInfoBinding;
import com.handpay.coupon.takephoto.TakePhoto;
import com.handpay.coupon.takephoto.TakePhotoImpl;
import com.handpay.coupon.takephoto.compress.CompressConfig;
import com.handpay.coupon.takephoto.model.CropOptions;
import com.handpay.coupon.takephoto.model.InvokeParam;
import com.handpay.coupon.takephoto.model.LubanOptions;
import com.handpay.coupon.takephoto.model.TContextWrap;
import com.handpay.coupon.takephoto.model.TImage;
import com.handpay.coupon.takephoto.model.TResult;
import com.handpay.coupon.takephoto.model.TakePhotoOptions;
import com.handpay.coupon.takephoto.permission.InvokeListener;
import com.handpay.coupon.takephoto.permission.PermissionManager;
import com.handpay.coupon.takephoto.permission.TakePhotoInvocationHandler;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.AssetsUtil;
import com.handpay.coupon.utils.DebouncingOnClickListener;
import com.handpay.coupon.utils.LogT;
import com.handpay.coupon.utils.RxToast;
import com.handpay.coupon.utils.glide.GlideUtils;
import com.handpay.coupon.view.SelectDialog;
import com.handpay.coupon.view.grideImage.GridImageView;
import com.handpay.coupon.view.grideImage.GridImageViewAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.Bitmap.CompressFormat.JPEG;

public class BranchInfo extends BaseActivity<ActivityBranchInfoBinding> implements TakePhoto.TakeResultListener, InvokeListener {

    private GridImageView<TImage> mGiv;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private String base64Str;
    private ACache mACache;
    public static final int UPLOAD_IMAGE = 110;
    private ArrayList<TImage> images;
    private boolean isCropable = false;//是否使用裁剪,Nexus 5,Lenovo Z2使用原生的裁剪工具
    private GetPoiBackBean.BusinessBean.BaseInfoBean baseInfoBean;
    private ArrayList<ProvincEBean> provinceList;
    private ArrayList<ProvincEBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private boolean isLoaded = false;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null && !isLoaded) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                LogT.w("省市区三级联动");
                                jsonToList();
                            }
                        });
                        thread.start();
                    }else{
                        RxToast.info("地区信息解析中，请稍候……");
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    showPickerView();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_info);

        isCropable = Build.MODEL.equals("Nexus 5") || Build.MODEL.equals("Lenovo Z2");
        showContentView();

        if (getIntent() != null && getIntent().getSerializableExtra("baseInfoBean") != null) {
            baseInfoBean = (GetPoiBackBean.BusinessBean.BaseInfoBean) getIntent().getSerializableExtra("baseInfoBean");
            modifyBranchInfo();
        } else {
            createBranch();
            bindingView.llMdAddress.setOnClickListener(new DebouncingOnClickListener() {
                @Override
                public void doClick(View v) {
                    LogT.w("跳转手机定位");
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    bundle.putDouble(BaseKey.KEY_LATITUDE, (double) mACache.getAsObject(BaseKey.KEY_LATITUDE));
                    bundle.putDouble(BaseKey.KEY_LONGTITUDE, (double) mACache.getAsObject(BaseKey.KEY_LONGTITUDE));
                    intent.putExtras(bundle);
                    intent.setClass(BranchInfo.this, MapActivity.class);
                    startActivityForResult(intent, 101);
                }
            });
            bindingView.llPcb.setOnClickListener(new DebouncingOnClickListener() {
                @Override
                public void doClick(View v) {
                    if(isLoaded){
                        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
                    }else{
                        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                    }
                }
            });
        }
        mACache = ACache.get(this);
//        mGiv = findViewById(R.id.giv);
        bindingView.giv.setAdapter(new GridImageViewAdapter<TImage>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, TImage path) {
                RxToast.info("上传服务器成功后调用请求图片列表接口");
//                LogT.w("图片原始地址=" + path.getOriginalPath() + "\n，如果图片超过100KB进行压缩，\n图片压缩地址=" + path.getCompressPath());
                GlideUtils.readCache(context, imageView, "file://" + path.getCompressPath(), mACache, "giv");
            }

            @Override
            protected void onAddClick(Context context, List<TImage> list) {
                setAlbum();
            }

            // 设置显示样式，网格/水平
            @Override
            protected int getShowStyle() {
                return GridImageView.STYLE_GRID;
            }

            @Override
            protected void onItemImageClick(Context context, int index, List<TImage> list) {
                super.onItemImageClick(context, index, list);
                Toast.makeText(getApplicationContext(), "--->" + index, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void jsonToList() {
        String temp = AssetsUtil.loadlocalData(BranchInfo.this, "province.json");
        if (TextUtils.isEmpty(temp)) {
            RxToast.info("获取temp失败：" + temp);
            return;
        }
        provinceList = new ArrayList<>();
        ProvincEBean getProvince = new Gson().fromJson(temp, ProvincEBean.class);
        provinceList.add(getProvince);
        options1Items = provinceList;


        for (int i = 0; i < provinceList.get(0).getProvince().size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> provinceAreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < provinceList.get(0).getProvince().get(i).getCity().size(); c++) {//遍历该省份的所有城市
                String cityName = provinceList.get(0).getProvince().get(i).getCity().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> cityAreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (provinceList.get(0).getProvince().get(i).getCity().get(c).getArea() == null
                        || provinceList.get(0).getProvince().get(i).getCity().get(c).getArea().size() == 0) {
                    cityAreaList.add("");
                } else {
                    cityAreaList.addAll(provinceList.get(0).getProvince().get(i).getCity().get(c).getArea());
                }
                provinceAreaList.add(cityAreaList);//添加该省所有地区数据
            }
            options2Items.add(cityList);// 添加城市数据
            options3Items.add(provinceAreaList);// 添加地区数据
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    // 弹出选择器
    private void showPickerView() {
//        LogT.w("显示列表：options1Items=" +options1Items.get(0).getProvince().size()+ ",options2Items=" + options2Items.size() + ",options3Items=" + options3Items.size());
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(0).getProvince().get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                bindingView.tvAddressSelect.setText(tx);
                bindingView.tvAddressSelect.setTextColor(getResources().getColor(android.R.color.black));
                RxToast.info(tx);
            }
        }).setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items.get(0).getProvince(), options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void createBranch() {
        setTitle("创建门店");
        bindingView.llPcb.setClickable(true);
        bindingView.btnUpload.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                RxToast.info("调用创建门店接口");
//                    startActivity(new Intent(BranchInfo.this, BusinessInfo.class));
//                    finish();
            }
        });
        bindingView.llCategories.setClickable(true);
        bindingView.llCategories.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                RxToast.info("选择门店类型");
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                intent.setClass(BranchInfo.this, FlowTagActivity.class);
                startActivityForResult(intent, 102);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void modifyBranchInfo() {
        setTitle("修改门店信息");
        bindingView.etBusinessName.setText(baseInfoBean.getBusiness_name());
        bindingView.etBranchName.setText(baseInfoBean.getBranch_name());
        bindingView.etBusinessName.setEnabled(false);
        bindingView.etBranchName.setEnabled(false);
        bindingView.tvAddressSelect.setText(baseInfoBean.getProvince()+baseInfoBean.getCity());
        bindingView.tvAddressSelect.setTextColor(getResources().getColor(android.R.color.black));
        bindingView.llPcb.setClickable(false);
        bindingView.llCategories.setClickable(false);
        bindingView.tvCategories.setText(baseInfoBean.getCategoriesString());
        bindingView.tvCategories.setTextColor(getResources().getColor(android.R.color.black));
        bindingView.etAddress.setEnabled(false);
        bindingView.etAddress.setText(baseInfoBean.getAddress());
        bindingView.llMdAddress.setClickable(false);
        bindingView.tvLocation.setText("("+baseInfoBean.getLatitude()+","+baseInfoBean.getLongitude()+")");
        bindingView.tvLocation.setTextColor(getResources().getColor(android.R.color.black));
        bindingView.btnUpload.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                RxToast.info("调用修改门店接口");
//                    startActivity(new Intent(BranchInfo.this, BusinessInfo.class));
                BranchInfo.this.finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UPLOAD_IMAGE:
                LogT.w("mgiv_setImageData");
                RxToast.info("调用上传图片接口");
//                List<TImage> list = data.getStringArrayListExtra("list");
//                //  PictureUtil.cropPhoto(this, Uri.parse("file://"+list.get(0)));
//                mGiv.setImageData(images, false);
//                List<TImage> l = mGiv.getImgDataList();
                break;
            case 101:
                LogT.w("调用MapActivity的回调");
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    bindingView.tvLocation.setTextColor(getResources().getColor(R.color.black));
                    bindingView.tvLocation.setText("(" + bundle.getDouble(BaseKey.KEY_LATITUDE, (double) mACache.getAsObject(BaseKey.KEY_LATITUDE))
                            + "," + bundle.getDouble(BaseKey.KEY_LONGTITUDE, (double) mACache.getAsObject(BaseKey.KEY_LONGTITUDE)) + ")");
                }
                break;
            case 102:
                LogT.w("调用FlowTagActivity的回调");
                if(data!=null){
                    Bundle bundle = data.getExtras();
                    bindingView.tvCategories.setTextColor(getResources().getColor(R.color.black));
                    bindingView.tvCategories.setText(bundle.getString(BaseKey.CATEGORIES));
                }
                break;
        }
    }

    private void setAlbum() {
        List<String> parms = new ArrayList<>();
        parms.add("相机");
        parms.add("相册");

        showDialog(new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://相机
                        takePhoto.onPickFromCaptureWithCrop(configCompress(takePhoto), getCropOptions(isCropable));
                        break;
                    case 1://相册，多选后批量压缩需要修改
                        takePhoto.onPickFromGalleryWithCrop(configCompress(takePhoto), getCropOptions(isCropable));
                        break;
                    default:
                        break;
                }
            }
        }, parms);
    }

    private Uri configCompress(TakePhoto takePhoto) {

        CompressConfig config;
//         /* 第一种压缩框架，没有监听 */
//        config = new CompressConfig.Builder()
//                .setMaxSize(1024 * 100)//压缩到的最大大小，单位B
//                .setMaxPixel(700)//长或宽不超过的最大像素,单位px
//                .enableReserveRaw(true)
//                .create();
//        takePhoto.onEnableCompress(config, true);
        /* 鲁班压缩+监听1 */
        LubanOptions option = new LubanOptions.Builder()
                .setMaxHeight(1920)
                .setMaxWidth(1000)
                .setMaxSize(1024 * 100)//压缩到的最大大小，单位B
                .create();
        config = CompressConfig.ofLuban(option);
        takePhoto.onEnableCompress(config, true);
        /* 裁剪 */
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        //联想机型设置为true的时候可能会找不到相册图片（部分机型可能存在问题）
        builder.setWithOwnGallery(true);
        builder.setCorrectImage(true);//图片角度调整
        takePhoto.setTakePhotoOptions(builder.create());
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    private CropOptions getCropOptions(boolean crop) {
        int height = Integer.parseInt(800 + "");
        int width = Integer.parseInt(800 + "");
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(width).setAspectY(height);
        builder.setOutputX(width).setOutputY(height);
        //裁剪 ,LG nexus5 手机要用自带的,不然找不到图片
        builder.setWithOwnCrop(crop);
        return builder.create();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * 获取TakePhoto实例
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult result) {
        String headUrl = result.getImage().getCompressPath();
        if (!TextUtils.isEmpty(headUrl)) {
            base64Str = bitmap2StrByBase64(BitmapFactory.decodeFile(headUrl));
            LogT.w("上传服务器_格式(StrByBase64),成功后显示图片");
        }
        images = new ArrayList<>();
//        images.add(result.getImage());
        if (null != result.getImage())
            images = result.getImages();
        bindingView.giv.setImageData(images, false);
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public String bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(JPEG, 100, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }
}
