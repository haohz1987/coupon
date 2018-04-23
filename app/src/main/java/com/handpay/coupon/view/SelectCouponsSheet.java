package com.handpay.coupon.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.handpay.coupon.R;

public class SelectCouponsSheet {

    public static Dialog dialog;
    private static Button btn_cancle;
    private static LinearLayout layout;

    public SelectCouponsSheet() {

    }

    public interface OnSheetSelected {
        void onSheetClick(int whichBtn);
    }

    public static Dialog showSheet(final Context context, final OnSheetSelected onSheetSelected,
                                   DialogInterface.OnCancelListener cancelListener) {
        if (dialog == null) {
            dialog = new Dialog(context, R.style.AlertDialogStyle);
        }

        dialog.setCanceledOnTouchOutside(true);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = (LinearLayout) inflater.inflate(R.layout.layout_pop, null);

        final int cFullFillWidth = 10000;
        layout.setMinimumWidth(cFullFillWidth);

        onClickId(onSheetSelected, R.id.btn_general);
        onClickId(onSheetSelected, R.id.btn_groupon);
        onClickId(onSheetSelected, R.id.btn_discount);
        onClickId(onSheetSelected, R.id.btn_gift);
        onClickId(onSheetSelected, R.id.btn_cash);
        onClickId(onSheetSelected, R.id.btn_member_card);
        onClickId(onSheetSelected, R.id.btn_scenic_ticket);
        onClickId(onSheetSelected, R.id.btn_movie_ticket);
        onClickId(onSheetSelected, R.id.btn_boarding_pass);
        onClickId(onSheetSelected, R.id.btn_lucky_money);
        onClickId(onSheetSelected, R.id.cancel);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        dialog.onWindowAttributesChanged(layoutParams);
        if (cancelListener != null)
            dialog.setOnCancelListener(cancelListener);
        dialog.setContentView(layout);
        dialog.show();
        return dialog;
    }

    private static void onClickId(final OnSheetSelected onSheetSelected, final int witchBtn) {
        layout.findViewById(witchBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSheetSelected.onSheetClick(witchBtn);
                dialog.dismiss();
            }
        });
    }
}
