package com.handpay.coupon.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.databinding.ActivityUploadImageBinding;
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
import com.handpay.coupon.utils.AndroidBug5497Workaround;
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

public class BranchInfo extends BaseActivity<ActivityUploadImageBinding> implements TakePhoto.TakeResultListener, InvokeListener {

    private GridImageView<TImage> mGiv;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private String base64Str;
    private ACache mACache;
    public static final int UPLOAD_IMAGE = 110;
    private ArrayList<TImage> images;
    private boolean isCropable = false;//是否使用裁剪,Nexus 5,Lenovo Z2使用原生的裁剪工具

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        if(isFullScreen(this)){
            AndroidBug5497Workaround.assistActivity(this);
        }
        isCropable = Build.MODEL.equals("Nexus 5") || Build.MODEL.equals("Lenovo Z2");
        showContentView();

        if(getIntent()!=null && getIntent().getSerializableExtra("baseInfoBean")!=null){
            modifyBranchInfo();
        }else{
            createBranch();
        }
        mACache = ACache.get(this);
        mGiv = findViewById(R.id.giv);
        mGiv.setAdapter(new GridImageViewAdapter<TImage>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, TImage path) {
                RxToast.info("上传服务器成功后调用请求图片列表接口");
                LogT.w("图片原始地址=" + path.getOriginalPath() + "\n，如果图片超过100KB进行压缩，\n图片压缩地址=" + path.getCompressPath());
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

    private void createBranch() {
        setTitle("创建门店");
        bindingView.btnUpload.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                RxToast.info("调用创建门店接口");
//                    startActivity(new Intent(BranchInfo.this, BusinessInfo.class));
//                    finish();

            }
        });
    }

    private void modifyBranchInfo() {
        setTitle("修改门店信息");
        bindingView.btnUpload.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                RxToast.info("调用修改门店接口");
//                    startActivity(new Intent(BranchInfo.this, BusinessInfo.class));
//                    finish();

            }
        });
    }

    public boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case UPLOAD_IMAGE:
                LogT.w("mgiv_setImageData");
                RxToast.info("调用上传图片接口");
//                List<TImage> list = data.getStringArrayListExtra("list");
//                //  PictureUtil.cropPhoto(this, Uri.parse("file://"+list.get(0)));
//                mGiv.setImageData(images, false);
//                List<TImage> l = mGiv.getImgDataList();
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
                .setMaxSize(1024 * 100)
                .create();
        config = CompressConfig.ofLuban(option);
        takePhoto.onEnableCompress(config, true);
        /* 裁剪 */
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        //联想机型设置为true的时候会找不到相册图片
//        if (Build.MODEL.equals("Lenovo Z2")) {
//            builder.setWithOwnGallery(false);
//        } else
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

//      String imgUrl = "http://img2.imgtn.bdimg.com/it/u=3588772980,2454248748&fm=27&gp=0.jpg";
//      GlideUtils.readCache(mContext, bindingView.imgUpload1, imgUrl, mACache, "upload_img");
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
