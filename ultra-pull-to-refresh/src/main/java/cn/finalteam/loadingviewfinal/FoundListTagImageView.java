package cn.finalteam.loadingviewfinal;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.finalteam.loadingviewfinal.uptr.R;


/**
 * 标签自定义View发现列表
 *
 * @author weizhang
 */
public class FoundListTagImageView extends RelativeLayout {
    private TagOnclickInterface interface1;
    private LayoutInflater mInflater;
    private String textTag;
    private Context mContext;

    public void setInterface1(TagOnclickInterface interface1) {
        this.interface1 = interface1;
    }

    public FoundListTagImageView(Context context) {
        this(context, null);
    }

    public FoundListTagImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FoundListTagImageView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    public void addTextTag(final String content, int x, int y, final int id,
                           final boolean flag, int color) {
        View view = mInflater.inflate(R.layout.activity_tag_tv, null);
        textTag = content;
        if (view == null) {
            return;
        }
        TextView text = (TextView) view.findViewById(R.id.tag_text);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_shouzhi);
        TextView tv_anmin = (TextView) view.findViewById(R.id.tv_anmin);
        /*tv_anmin.setBackgroundColor(color);*/
        GradientDrawable myGrad = (GradientDrawable) tv_anmin.getBackground();
        myGrad.setColor(color);
        tv_anmin.startAnimation(getNewAnimationSet());

        RelativeLayout layout = (RelativeLayout) view
                .findViewById(R.id.tag_layout);
        text.setTextColor(color);
        text.setText(textTag);
        Animation mAnimation = AnimationUtils.loadAnimation(mContext,
                R.anim.tag_anim);
        layout.setAnimation(mAnimation);
        text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    interface1.onclick(content, id);
                }
            }
        });
        this.addView(layout);
        setPosition(layout, x, y, content);
    }

    private AnimationSet getNewAnimationSet() {
        AnimationSet as = new AnimationSet(true);
        ScaleAnimation sa = new ScaleAnimation(1f, 2.3f, 1f, 2.3f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(1500);
        sa.setRepeatCount(-1);// 设置循环
        AlphaAnimation aniAlp = new AlphaAnimation(1, 0.1f);
        aniAlp.setRepeatCount(-1);// 设置循环
        as.setDuration(1500);
        as.addAnimation(sa);
        as.addAnimation(aniAlp);
        return as;
    }

    private void setPosition(View v, int dx, int dy, String content) {
        LayoutParams params = (LayoutParams) v
                .getLayoutParams();
        params.leftMargin = dx;
        if (dy + v.getHeight() > getHeight()) {
            dy = dy - v.getHeight();
        }
        params.topMargin = dy;
        v.setLayoutParams(params);
    }

    public interface TagOnclickInterface {
        public void onclick(String text, int id);
    }
}
