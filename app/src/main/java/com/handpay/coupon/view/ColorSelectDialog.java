package com.handpay.coupon.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.handpay.coupon.R;
import com.handpay.coupon.base.CouponColorType;

import java.util.ArrayList;
import java.util.List;

public class ColorSelectDialog {

    private static LinearLayout layout;
    private static Dialog dlg;
    private static int selectPosition = -1;
    //卡券的颜色
    private static List<Integer> couponColorRes = new ArrayList<>();
    //卡券的类型
    private static List<String> couponType = new ArrayList<>();
    private static ImageView imageView;
    private static RelativeLayout rl_select;
    private static FinalAdapter finalAdapter;

    public interface OnColorSelected {
        void onColorSelected(int position);
    }

    public ColorSelectDialog() {
    }

    public static Dialog selectColor(final Context context, final OnColorSelected onColorSelected) {//,OnCancelListener cancelListener
        if(dlg==null){
            dlg = new Dialog(context, R.style.AlertDialogStyle);
        }
        dlg.setCanceledOnTouchOutside(true);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = (LinearLayout) inflater.inflate(R.layout.dialog_color_selector, null);
        final int cFullFillWidth = 10000;
        layout.setMinimumWidth(cFullFillWidth);

        layout.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
        //完成
        layout.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectPosition == -1) {
                    Toast.makeText(context, "请选择一个颜色", Toast.LENGTH_SHORT).show();
                    return;
                }
                onColorSelected.onColorSelected(selectPosition);
                dlg.dismiss();
            }
        });

        //添加10类优惠券颜色
        couponColorRes.clear();
        couponType.clear();
        for (int i = 1; i <= 10; i++) {
            couponColorRes.add(CouponColorType.getResID("" + i));
            couponType.add("" + i);
        }

        final MyGridView mGridView = layout.findViewById(R.id.mgridview);
        finalAdapter = new FinalAdapter<Integer>(couponColorRes, couponType, R.layout.item_gridview, new FinalAdapter.OnAdapterListener() {
            @Override
            public void bindView(FinalAdapter.FinalViewHolder finalViewHolder, Object content, String tag,int position) {
                imageView = (ImageView) finalViewHolder.getViewById(R.id.gridview_image);
                imageView.setImageResource(((Integer) content));
                rl_select = (RelativeLayout) finalViewHolder.getViewById(R.id.rl_select);
                rl_select.setClickable(false);
                if (position == selectPosition) {
                    rl_select.setBackgroundResource(R.mipmap.selected);
                } else {
                    rl_select.setBackgroundColor(0);
                }
            }
        });
        mGridView.setAdapter(finalAdapter);
        mGridView.setNumColumns(5);
        mGridView.setSelection(-1);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rl_select.setClickable(true);
                selectPosition = position;
                rl_select.setBackgroundResource(R.mipmap.selected);
                finalAdapter.notifyDataSetChanged();
            }
        });
        Window w = dlg.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        dlg.onWindowAttributesChanged(lp);
//        if (cancelListener != null)
//            dlg.setOnCancelListener(cancelListener);
        dlg.setContentView(layout);
        dlg.show();
        return dlg;
    }

}
