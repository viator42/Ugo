package com.viator42.ugo.module.ref;

import android.content.Context;
import android.content.SharedPreferences;

import com.viator42.ugo.model.User;

import java.util.HashSet;
import java.util.Set;

public class RefAction
{
    public void setUser(Context context, User user)
    {
        //用户信息写入ref
        SharedPreferences ref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ref.edit();
        editor.putLong("userId", user.userId);
        editor.putString("userName", user.userName);
        editor.putString("mobile", user.mobile);
        editor.putString("sessionid", user.sessionid);
        editor.putString("headImg", user.headImg);
        editor.putString("address", user.address);
        editor.putString("birthday", user.birthday);
        editor.putString("newUserId", user.newUserId);
        editor.commit();

    }

    public User getUser(Context context)
    {
        User user = null;
        SharedPreferences ref = context.getSharedPreferences("user", Context.MODE_PRIVATE);

        if(ref != null)
        {
            long userId = ref.getLong("userId", 0);
            if(userId != 0)
            {
                user = new User();
                user.userId = userId;
                user.userName = ref.getString("userName", null);
                user.mobile = ref.getString("mobile", null);
                user.sessionid = ref.getString("sessionid", null);
                user.headImg = ref.getString("headImg", null);
                user.address = ref.getString("address", null);
                user.birthday = ref.getString("birthday", null);
                user.newUserId = ref.getString("newUserId", null);
            }
        }

        return user;
    }

    public void removeUser(Context context)
    {
        SharedPreferences ref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ref.edit();
        editor.clear();

        editor.commit();
    }

    //是否是首次开启
    public boolean firstOpen(Context context)
    {
        boolean result = true;

        User user = null;
        SharedPreferences ref = context.getSharedPreferences("first_open", Context.MODE_PRIVATE);

        if(ref != null)
        {
            result = ref.getBoolean("first_open", true);

            if(result == true)
            {
                SharedPreferences.Editor editor = ref.edit();
                editor.putBoolean("first_open", false);

                editor.commit();
            }
        }

        return result;
    }

}
