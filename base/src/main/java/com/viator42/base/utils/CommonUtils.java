package com.viator42.base.utils;

import android.content.Context;
import android.widget.Toast;

public class CommonUtils {
    public static void makeToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void makeToast(Context context, int resId) {
        Toast.makeText(context, context.getResources().getString(resId), Toast.LENGTH_SHORT).show();
    }
}
