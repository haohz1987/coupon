package com.handpay.coupon.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.handpay.coupon.utils.LogT;


/**
 * @author ryze
 */
public class AutoSwitchView extends AutoLoopSwitchBaseView {
    public AutoSwitchView(Context context) {
        super(context);
    }

    public AutoSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**
     * 页面切换回调
     */
    @Override
    protected void onSwitch(int index, Object object) {
        LogT.w("onSwitch:index="+index+",Object="+object.toString());
//        FragmentHomeBean.AttrBean.BannersBean model = (FragmentHomeBean.AttrBean.BannersBean) object;
//        if (model != null) {
//            ImageView imageView = new ImageView(getContext());
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(getContext()).load(model.getImageUrl()).into(imageView);
//        }
    }
    @Override
    protected View getFailtView() {
        return null;
    }

    //轮播时间间隔
    @Override
    protected long getDurtion() {
        return 3000;
    }

    @Override
    public void setAdapter(AutoLoopSwitchBaseAdapter adapter) {
        super.setAdapter(adapter);
        mHandler.sendEmptyMessage(LoopHandler.MSG_REGAIN);
    }
}