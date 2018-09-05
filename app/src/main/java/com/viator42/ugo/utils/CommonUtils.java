package com.viator42.ugo.utils;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * 一般工具类
 */
public class CommonUtils {
    public static void makeToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void log(String msg) {
        Log.v("Ugo", msg);
    }

    //字符串是否为空值
    public static boolean isValueEmpty(String str)
    {
        return str == null || str.isEmpty() || str.equals("null");
    }

    public static Map<String, Object> generateParams(String jsonString, String aesKey)
    {
        //加密
        if(!CommonUtils.isValueEmpty(aesKey))
        {
            try {
                Log.v("beforeEncryped", jsonString);
                Log.v("requestAesKey", aesKey);
                jsonString = AESOperator.encrypt(jsonString, aesKey);
//                jsonString = AESUtils.encrypt(CommonUtils.base64Decode(aesKey), jsonString);
//                jsonString = CommonUtils.base64Encode(jsonString);
                Log.v("encrype", jsonString);
            } catch (Exception e) {
                e.printStackTrace();
                Log.v("encryped", "Failed");
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("param", jsonString);

        return map;
    }

    /**
     * base64加解密
     */
    public static String base64Encode(String str)
    {
        return new String(Base64.encode(str.getBytes(), Base64.DEFAULT));
    }

    public static byte[] base64Decode(String str)
    {
        return Base64.decode(str.getBytes(), Base64.DEFAULT);
    }

}
