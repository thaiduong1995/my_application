package com.thaiduong.myapplication.data_local;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreferences {
    private static final String LOGIN_STATUS = "LOGIN_STATUS";
    private static final String ACCOUNT = "ACCOUNT";
    private Context mContext;

    public AppSharedPreferences(Context mContext) {
        this.mContext = mContext;
    }

    public void putLoginStatus(String key, boolean value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(LOGIN_STATUS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getLoginStatus(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(LOGIN_STATUS, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public void putAccount(String key, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(ACCOUNT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getAccount(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(ACCOUNT, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

}
