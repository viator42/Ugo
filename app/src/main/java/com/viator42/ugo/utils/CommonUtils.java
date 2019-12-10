package com.viator42.ugo.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
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
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
    }

    public static void makeToast(Context context, int resId) {
        Toast.makeText(context, context.getResources().getString(resId), Toast.LENGTH_SHORT).show();
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
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

    /**
     * 获取渠道信息
     */
    public static HashMap<String, Object> getChannelInfo(Context context) {
        HashMap<String, Object> result = new HashMap<String, Object>();

        String pkgName = context.getPackageName();
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(pkgName, PackageManager.GET_META_DATA);
            Bundle metaData = applicationInfo.metaData;
            result.put("channel_id", metaData.get("channel_id"));
            result.put("app_name", metaData.get("app_name"));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 长文字截取内容,在末尾添加省略号
     * @param content 原文
     * @param maxLength 截取的最大长度
     * @return 截取后的文字内容
     */
    public static String omissionText(String content, int maxLength) {
        String result = content;
        //处理文字长度
        if(maxLength > 0) {
            if (content != null && content.length() > maxLength) {
                result = content.substring(0, maxLength);
                String[] endMarks = {"！", "!", "。", ".","，", ","};
                int splitPt = 0;
                for (String endMark: endMarks) {
                    int pt = result.lastIndexOf(endMark);
                    if(pt > 0) {
                        if(pt > splitPt) {
                            splitPt = pt;
                        }
                    }
                }

                if (splitPt > 0) {
                    result = result.substring(0, splitPt);
                    result += "...";
                }
            }
        }
        else {
            return content;
        }

        return result;
    }

}
