package com.handpay.coupon.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

public class DialogFactory {
	//提示对话框，要求实例唯一
	private static AlertDialog dialog = null;
	
	// 进度条
	private static ProgressDialog progressDialog = null;
	
	/**
	 * 显示ProgressDialog
	 * 
	 * @param title
	 *            标题
	 * @param message
	 *            提示信息
	 * @param style
	 *            样式
	 * @param cancelable
	 *            是否能取消
	 * @param max
	 *            最大值
	 */
	public static ProgressDialog showProgressDialog(Context context,
                                                    String title,
                                                    String message,
                                                    boolean cancelable,
                                                    int style,
                                                    int max){
		try
		{
			//bug: View not attached to window manager
			//solution refer: http://stackoverflow.com/questions/2745061/java-lang-illegalargumentexception-view-not-attached-to-window-manager
			try {
				if (progressDialog != null){
                   LogT.w("actually dismiss progress dialog");
					progressDialog.dismiss();// 关闭progressDialog
					progressDialog = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			progressDialog = new ProgressDialog(context);
			progressDialog.setTitle(title);
			progressDialog.setMessage(message);
			progressDialog.setProgressStyle(style);
			progressDialog.setCancelable(cancelable);
			progressDialog.setMax(max);
			LogT.w("actually show progress dialog");
			progressDialog.show();
            return progressDialog;
		} catch (Exception e) {
			LogT.w( "RunnableShowProgressDialog", e);
            return null;
		}
	}
	
	/**
	 * 显示ProgressDialog
	 * @param context
	 * @param title
	 * @param message
	 * @param cancelable
	 * @param style
	 */
	public static ProgressDialog showProgressDialog(Context context,
                                                    String title,
                                                    String message,
                                                    boolean cancelable,
                                                    int style){
		return showProgressDialog(context, title, message, cancelable, style, 100);
	}
	
	/**
	 * 显示ProgressDialog
	 * @param context
	 * @param title
	 * @param message
	 * @param cancelable
	 */
	public static ProgressDialog showProgressDialog(Context context,
                                                    String title,
                                                    String message,
                                                    boolean cancelable)
	{
        return showProgressDialog(context, title, message, cancelable, ProgressDialog.STYLE_SPINNER);
	}
	
	/**
	 * 显示ProgressDialog
	 * @param context
	 * @param title
	 * @param message
	 */
	protected static ProgressDialog showProgressDialog(Context context,
                                                       String title,
                                                       String message){
        return showProgressDialog(context, title, message, false);
	}
	
	/**
	 * 显示ProgressDialog
	 * @param context
	 * @param message
	 */
	public static ProgressDialog showProgressDialog(Context context, String message){
		return showProgressDialog(context, null, message);
	}
	
	/**
	 * 更新ProgressDialog的Progress值
	 * @param value
	 */
	public void updateProcess(int value){
		if (progressDialog != null){
			if (value >= progressDialog.getMax()){
				closeProgressDialog();
			}else{
				progressDialog.setProgress(value);
			}
		}
	}
	
	/**
	 * 关闭ProgressDialog
	 */
	public static void closeProgressDialog(){
		try {
			if (progressDialog != null){
				LogT.w("actually dismiss progress dialog");
				progressDialog.dismiss();// 关闭progressDialog
				progressDialog = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			progressDialog = null;
		}
	}
	
	/**
	 * 显示提示对话框
	 * @param context
	 * @param title
	 *            标题
	 * @param message
	 *            提示信息
	 * @param cancelable
	 *            是否可取消
	 * @param oclOK
	 *            确定事件
	 * @param oclCancel
	 *            取消事件
	 * @param middleButton
	 *            中间按钮文字
	 * @param oclMiddle
	 *            中间按钮事件
	 */
	public static void showAlertDialog(Context context,
										 String title,
										 String message,
										 boolean cancelable, 
										 OnClickListener oclOK,
										 OnClickListener oclCancel,
										 String middleButton,
										 OnClickListener oclMiddle){
		try
		{
			if (dialog != null && dialog.isShowing()) {
				LogT.w("ignore show request");
				return;
			}
			AlertDialog.Builder adb = new AlertDialog.Builder(context);
			
			adb.setTitle(title);
			adb.setMessage(message);
			// 按钮文字为空时，不显示按钮
			adb.setPositiveButton(android.R.string.ok, oclOK);
			adb.setNeutralButton(middleButton, oclMiddle);
			if (oclCancel != null){
				// 取消事件为空，认为是没有取消按钮
				adb.setNegativeButton(android.R.string.cancel, oclCancel);
			}
			adb.setCancelable(cancelable);
			dialog = adb.create();
			dialog.show();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示提示对话框
	 * @param context
	 * @param title
	 * @param message
	 * @param cancelable
	 * @param oclOK
	 * @param oclCancel
	 */
	public static void showAlertDialog(Context context,
								String title,
								String message,
								boolean cancelable, 
								OnClickListener oclOK,
								OnClickListener oclCancel){
		showAlertDialog(context, title, message, cancelable, oclOK, oclCancel, null, null);
	}
	
	/**
	 * 显示提示对话框
	 * @param context
	 * @param title
	 * @param message
	 * @param cancelable
	 * @param oclOK
	 */
	public static void showAlertDialog(Context context,
									  String title,
									  String message,
									  boolean cancelable, 
									  OnClickListener oclOK){
		showAlertDialog(context, title, message, cancelable, oclOK, null);
	}
	
	/**
	 * 显示提示对话框
	 * @param context
	 * @param title
	 * @param message
	 * @param cancelable
	 */
	public static void showAlertDialog(Context context,
								String title,
								String message,
								boolean cancelable){
		showAlertDialog(context, title, message, cancelable, null);
	}
	
	/**
	 * 显示提示对话框
	 * @param context
	 * @param title
	 * @param message
	 */
	public static void showAlertDialog(Context context,
								String title,
								String message){
		showAlertDialog(context, title, message, true, null);
	}
	
	/**
	 * 显示提示对话框
	 * @param context
	 * @param title
	 * @param message
	 */
	public static void showAlertDialog(Context context, int title, int message){
		showAlertDialog(context, context.getString(title), context.getString(message), true, null);
	}
	
	/**
	 * 显示提示对话框
	 * @param context
	 * @param title
	 */
	protected static void showAlertDialog(Context context, String title){
		showAlertDialog(context, title, null, true, null);
	}
	
	/**
	 * 显示提示对话框
	 * @param context
	 * @param title
	 */
	protected static void showAlertDialog(Context context, int title){
		showAlertDialog(context, context.getString(title));
	}
	
	/**
	 * 显示提示对话框
	 * @param context
	 * @param title
	 * @param oclOK
	 */
	protected static void showAlertDialog(Context context, String title, OnClickListener oclOK){
		showAlertDialog(context, title, null, false, oclOK);
	}
}
