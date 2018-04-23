package com.handpay.coupon.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.base.BaseKey;
import com.handpay.coupon.databinding.ContentMainBinding;
import com.handpay.coupon.utils.DebouncingOnClickListener;
import com.handpay.coupon.utils.LogT;
import com.handpay.coupon.utils.RxToast;
import com.handpay.coupon.view.flowtag.FlowTagLayout;
import com.handpay.coupon.view.flowtag.OnTagSelectListener;
import com.handpay.coupon.view.flowtag.TagAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 调交时可以用数据库保存每个大类的选项
 */

public class FlowTagActivity extends BaseActivity<ContentMainBinding> implements OnTagSelectListener {

    private TagAdapter<String> ftl1Adapter, ftl2Adapter, ftl3Adapter, ftl4Adapter, ftl5Adapter, ftl6Adapter, ftl7Adapter, ftl8Adapter, ftl9Adapter;
    private List<String> ftl1List, ftl2List, ftl3List, ftl4List, ftl5List, ftl6List, ftl7List, ftl8List, ftl9List;
    private static final int MSG_CLICK = 0x0003;

    private String[] flt1Str = {"粤菜", "茶餐厅", "川菜", "湘菜", "东北菜", "西北菜", "火锅", "自助餐", "小吃", "快餐"
            , "日本料理", "韩国料理", "东南亚菜", "西餐", "面包甜点", "咖啡厅", "江浙菜", "其他美食"};
    private String[] flt2Str = {"美容美发", "美甲", "艺术写真", "酒吧/俱乐部", "文化文艺", "KTV", "棋牌室", "运动健身", "足疗按摩", "演出门票"};
    private String[] flt3Str = {"百货商场", "超市/便利店", "婚庆服务", "汽车服务", "家政", "物业管理", "医疗保健", "物流"};
    private String[] flt4Str = {"机票"};
    private String[] flt5Str = {"电影票"};
    private String[] flt6Str = {"景点门票", "旅游套餐"};
    private String[] flt7Str = {"星级酒店", "度假村", "快捷酒店"};
    private String[] flt8Str = {"服饰", "鞋类箱包", "运动户外", "化妆品", "珠宝配饰", "家纺家装", "数码家电", "乐器", "鲜花", "礼品", "普通食品"
            , "保健食品", "酒类", "母婴用品", "图书报刊杂志"};
    private String[] flt9Str = {"话费"};
    private StringBuilder sb;

//    @SuppressLint("HandlerLeak")
//    private Handler mHandler = new Handler() {
//        @SuppressLint("SetTextI18n")
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case MSG_CLICK:
//                    LogT.w("选择：" + sb.toString());
//                    bindingView.tvTip.setText("选择的门店类型：" + sb.toString());
//                    break;
//
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        setTitle("选择门店类型");
        showContentView();
        initAdapter();
        initData();
        bindingView.btnClear.setOnClickListener(new DebouncingOnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void doClick(View v) {
                bindingView.ftl1.clearAllOption();
                bindingView.ftl2.clearAllOption();
                bindingView.ftl3.clearAllOption();
                bindingView.ftl4.clearAllOption();
                bindingView.ftl5.clearAllOption();
                bindingView.ftl6.clearAllOption();
                bindingView.ftl7.clearAllOption();
                bindingView.ftl8.clearAllOption();
                bindingView.ftl9.clearAllOption();
                sb.setLength(0);
                sb = new StringBuilder();
                RxToast.info("选择已清空");
                bindingView.tvTip.setText("选择的门店类型：" + sb.toString());
            }
        });
        bindingView.btnSubmit.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                if(TextUtils.isEmpty(sb.toString())){
                    RxToast.warning("请选择当前门店的类型");
                    return;
                }
                Intent intent = FlowTagActivity.this.getIntent();
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    sb.setLength(sb.length() - 1);
                    bundle.putString(BaseKey.CATEGORIES,sb.toString());
                    intent.putExtras(bundle);
                }
                FlowTagActivity.this.setResult(102,intent);
                FlowTagActivity.this.finish();
            }
        });
    }

    private void initData() {
        ftl1List = new ArrayList<>();
        ftl2List = new ArrayList<>();
        ftl3List = new ArrayList<>();
        ftl4List = new ArrayList<>();
        ftl5List = new ArrayList<>();
        ftl6List = new ArrayList<>();
        ftl7List = new ArrayList<>();
        ftl8List = new ArrayList<>();
        ftl9List = new ArrayList<>();

        Collections.addAll(ftl1List, flt1Str);
        Collections.addAll(ftl2List, flt2Str);
        Collections.addAll(ftl3List, flt3Str);
        Collections.addAll(ftl4List, flt4Str);
        Collections.addAll(ftl5List, flt5Str);
        Collections.addAll(ftl6List, flt6Str);
        Collections.addAll(ftl7List, flt7Str);
        Collections.addAll(ftl8List, flt8Str);
        Collections.addAll(ftl9List, flt9Str);

        ftl1Adapter.onlyAddAll(ftl1List);
        ftl2Adapter.onlyAddAll(ftl2List);
        ftl3Adapter.onlyAddAll(ftl3List);
        ftl4Adapter.onlyAddAll(ftl4List);
        ftl5Adapter.onlyAddAll(ftl5List);
        ftl6Adapter.onlyAddAll(ftl6List);
        ftl7Adapter.onlyAddAll(ftl7List);
        ftl8Adapter.onlyAddAll(ftl8List);
        ftl9Adapter.onlyAddAll(ftl9List);
    }

    private void initAdapter() {
        ftl1Adapter = new TagAdapter<>(this);
        ftl2Adapter = new TagAdapter<>(this);
        ftl3Adapter = new TagAdapter<>(this);
        ftl4Adapter = new TagAdapter<>(this);
        ftl5Adapter = new TagAdapter<>(this);
        ftl6Adapter = new TagAdapter<>(this);
        ftl7Adapter = new TagAdapter<>(this);
        ftl8Adapter = new TagAdapter<>(this);
        ftl9Adapter = new TagAdapter<>(this);

        bindingView.ftl1.setAdapter(ftl1Adapter);
        bindingView.ftl2.setAdapter(ftl2Adapter);
        bindingView.ftl3.setAdapter(ftl3Adapter);
        bindingView.ftl4.setAdapter(ftl4Adapter);
        bindingView.ftl5.setAdapter(ftl5Adapter);
        bindingView.ftl6.setAdapter(ftl6Adapter);
        bindingView.ftl7.setAdapter(ftl7Adapter);
        bindingView.ftl8.setAdapter(ftl8Adapter);
        bindingView.ftl9.setAdapter(ftl9Adapter);

        bindingView.ftl1.setOnTagSelectListener(this);
        bindingView.ftl2.setOnTagSelectListener(this);
        bindingView.ftl3.setOnTagSelectListener(this);
        bindingView.ftl4.setOnTagSelectListener(this);
        bindingView.ftl5.setOnTagSelectListener(this);
        bindingView.ftl6.setOnTagSelectListener(this);
        bindingView.ftl7.setOnTagSelectListener(this);
        bindingView.ftl8.setOnTagSelectListener(this);
        bindingView.ftl9.setOnTagSelectListener(this);
    }
    @Override
    public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
        sb = new StringBuilder();
        if (selectedList != null && selectedList.size() > 0) {
            for (int i : selectedList) {
                sb.append(parent.getAdapter().getItem(i));
                LogT.w("i=" + i + ",selectedList.size()=" + selectedList.size());
                sb.append(",");
                //去除最后一位","
//                if(selectedList.size()==1){
//                    sb.setLength(sb.length() - 1);
//                }
            }
//            mHandler.sendEmptyMessage(MSG_CLICK);
            LogT.w("选择：" + sb.toString());
            bindingView.tvTip.setText("选择的门店类型：" + sb.toString());
        } else {
            LogT.w("没有选择标签");
        }
    }
}