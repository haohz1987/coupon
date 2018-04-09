package com.handpay.coupon.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ClientEngine {
    private Context mContext;
    public static final String VOICE_PLUGIN = "voice_plugin";            //音频口状态的key
    private static final String SHAREPRE_NAME = "RMS";
    // 服务端地址
//    public static String APPSERVERURL = ZZTConfig.APPSERVER;
    // 客户端版本号
    public static String VERSION = "1.0.0";
    public static String SWITCH_TYPE = "1";
    public static final String MOBILETYPE = "ANDROID";
    public static final String FULLMOBILETYPE = Build.MODEL;
//    public static String CHANNEL = ZZTConfig.CHANNEL;
//    public static String HPVIRSION = ZZTConfig.HPVIRSION;
    private static final char RMS_STRING = 's';
    private static final char RMS_NUMBER = 'n';
    private static final char RMS_BOOLEAN = 'b';
    private static final char RMS_TABLE = 't';
    private static final char RMS_INTEGER = 'i';
    private static String IMEI;
    private static String MAC;
    private static String IP;
    public static final String KEY_CLIENT_AGREEMENT="agreement";
    @SuppressLint("MissingPermission")
    public String getIMEI() {
        if (mContext == null) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(IMEI)) {
                TelephonyManager telephony = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                IMEI = telephony.getDeviceId();
            }
            return IMEI;
        } catch (Exception e) {
            LogT.w("IMEI,"+ e);
        }
        return IMEI;
    }

    public String getMac() {
        if (mContext == null) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(MAC)) {
                WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
                @SuppressLint("MissingPermission") WifiInfo info = wifi.getConnectionInfo();
                if (null != info) {
                    MAC = info.getMacAddress();
                }
            }
            return MAC;
        } catch (Exception e) {
            LogT.w("MAC,"+ e);
        }
        return MAC;
    }

    /**
     * 终端设备唯一标识：0/1 + 32位十六进制字符串；(0：iOS；1：Android)
     * 2017-03-10 修改不写入setting.db
     * @return
     */
    public String getDeviceUuid() {
        String terminalUnique = null;
        if (null == mContext) {
            return null;
        }
        terminalUnique=String.valueOf(getRMSData("X-HPTUDID")==null?"":getRMSData("X-HPTUDID"));
        if (null==terminalUnique||terminalUnique.length()<=2||terminalUnique.equals("null")) {
            UUID uuidstring = UUID.randomUUID();
            String uuid = "1" + uuidstring.toString().replace("-", "");
            saveRMSData("X-HPTUDID",uuid);
            terminalUnique = uuid;
        }
        return terminalUnique;
    }

    private ConcurrentHashMap<String, Object> hashGlobal = new ConcurrentHashMap<String, Object>();

    private static ClientEngine ce;

    public static ClientEngine getInstance() {
        if (ce == null) {
            ce = new ClientEngine();
        }
        return ce;
    }

    public void onDestroy() {
        ce = null;
    }


    /**
     * 初始化，必须最先调用
     *
     * @param context      项目的Activity
     * @param hpversion    服务端协议版本号
     * @param appServerUrl 服务端地址
     * @param channel      渠道号
     */
    public void init(Context context, String hpversion, String appServerUrl, String channel) {
        this.mContext = context;
//        HPVIRSION = hpversion;
//        APPSERVERURL = appServerUrl;
//        CHANNEL = channel;
    }

    public int getVersionCode() {
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), PackageManager.PERMISSION_GRANTED);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Context getActivity() {
        return mContext;
    }

    /**
     * 保存数据为全局
     *
     * @param key
     * @param data
     * @return
     */
    public void saveGlobal(String key, Object data) {
        if (hashGlobal.containsKey(key)) {
            hashGlobal.remove(key);
        }
        if (data != null) {
            hashGlobal.put(key, data);
        }
    }

    public void clearGlobal() {
        hashGlobal.clear();
    }

    /**
     * 读取全局数据
     *
     * @param key
     * @return
     */
    public Object getGlobal(String key) {
        return getGlobal(key, "");
    }

    public Object getGlobal(String key, Object defaultValue) {
        if (hashGlobal.containsKey(key)) {
            return hashGlobal.get(key);
        } else {
            return defaultValue;
        }
    }


    /**
     * 读取本地保存的数据
     *
     * @return
     */
    public synchronized Object getRMSData(String key) {
        if (mContext == null) {
            return null;
        }

        SharedPreferences preferences = mContext.getSharedPreferences(SHAREPRE_NAME, Context.MODE_PRIVATE);
        if (!preferences.contains(key)) {
            return null;
        }
        try {
            String result = preferences.getString(key, "");
            if (TextUtils.isEmpty(result) || result.length() < 2) {
                return null;
            }
            char type = result.charAt(0);

            Object robj = null;
            switch (type) {
                case RMS_STRING:
                    robj = result.substring(1);
                    break;
                case RMS_TABLE:
                    byte[] resp1 = ("data=" + result.substring(1)).getBytes("UTF-8");
//                    LuaTableUtil.stringToLuaTable(resp1);
                    break;
                case RMS_NUMBER:
                    robj = Double.valueOf(Double.parseDouble(result.substring(1)));
                    break;
                case RMS_INTEGER:
                    robj = Double.valueOf(Integer.parseInt(result.substring(1)));
                    break;
                case RMS_BOOLEAN:
//                    robj = LuaState.toBoolean(result.substring(1).equals(Boolean.TRUE.toString()));
                    break;
            }
            if (robj != null) {
                hashGlobal.put(key, robj);
            }
            return robj;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 移除某个数据
     *
     * @param key
     */
    public synchronized void removedData(String key) {
        if (mContext == null) {
            return;
        }
        SharedPreferences prefer = mContext.getSharedPreferences(SHAREPRE_NAME, Context.MODE_PRIVATE);
        if (prefer.contains(key)) {
            SharedPreferences.Editor editor = prefer.edit();
            editor.remove(key);
            editor.commit();
        }
    }

    /**
     * 保存数据到本地
     *
     * @param key
     * @param data
     * @return
     */
    public synchronized void saveRMSData(String key, Object data) {
        if (mContext == null) {
            return;
        }
        SharedPreferences prefer = mContext.getSharedPreferences(SHAREPRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefer.edit();
        if (key != null) {
            editor.remove(key);
            editor.commit();
        }
        if (data == null) {
            return;
        }
        String put = null;

        if (data instanceof String) {
            put = (RMS_STRING + (String) data);
        }
//        else if (data instanceof LuaTable) {
//            put = (RMS_TABLE + LuaTableUtil.luaTableToString((LuaTable) data));
//        }
        else if (data instanceof Double) {
            put = (RMS_NUMBER + Double.toString(((Double) data).doubleValue()));
        } else if (data instanceof Integer) {
            put = (RMS_INTEGER + Integer.toString(((Integer) data).intValue()));
        } else if (data instanceof Boolean) {
            if (((Boolean) data).booleanValue()) {
                put = (RMS_BOOLEAN + Boolean.TRUE.toString());
            } else {
                put = (RMS_BOOLEAN + Boolean.FALSE.toString());
            }
        }
        if (put != null) {
            editor.putString(key, put);
            editor.commit();
        }
    }


    public void doThread(Thread thread) {
        thread.start();
    }

    // 在UI线程做操作
    public void doUiThread(Runnable runnable, Activity activity) {
        if (activity != null) {
            activity.runOnUiThread(runnable);
        }
    }

    private class ExitRunnable implements Runnable {

        @Override
        public void run() {
            exit();
        }

    }

    public void browse(Activity activity, String url, boolean close) throws Exception {
        if (mContext != null) {
            Uri uri = Uri.parse(url);
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(it);
        }
        if (close) {
            doUiThread(new ExitRunnable(), activity);
        }
    }


    /**
     * 判断应用是否存在
     *
     * @param packageName
     * @return
     */
    public boolean checkBrowser(Context context, String packageName) {
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public void callPhone(String phonenumber) {
        if (mContext != null) {
            // Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +
            // phonenumber));//调出拨号程序
            Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phonenumber));
            mContext.startActivity(it);
        }
    }

    // Toast
    public static void showToast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static void showToast(Context context, String message) {
        showToast(context, message, Toast.LENGTH_LONG);
    }

    public void showToast(String message) {
        showToast(mContext, message);
    }

    public void exit() {
        if (mContext != null) {
            LogT.w("ClientEngine," +"exit account");
            mContext = null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        LogT.w("==============ClientEngine finalize!");
        super.finalize();
    }

    public String getString(int stringId) {
        if (null != mContext) {
            return mContext.getString(stringId);
        } else {
            return null;
        }
    }
}