package com.handpay.coupon.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by haohz on 2018/1/29.
 */

public class AssetsUtil {
    public static String loadlocalData(Context context,String assetsJson) {
        AssetManager assetManager = context.getAssets();
        try {
            // InputStream is = assetManager.open("city.json");
            InputStream is = assetManager.open(assetsJson);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuffer = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            LogT.w("assertsJson 解析错误_"+e.getMessage());
            return null;
        }
    }
}