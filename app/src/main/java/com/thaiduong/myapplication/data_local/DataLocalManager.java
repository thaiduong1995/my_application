package com.thaiduong.myapplication.data_local;

import android.content.Context;

import com.google.gson.Gson;
import com.thaiduong.myapplication.login_register.model.AppAccount;

public class DataLocalManager {
    private static final String PREF_LOGIN_STATUS = "PREF_LOGIN_STATUS";
    private static final String PREF_ACCOUNT = "PREF_ACCOUNT";
    private static DataLocalManager instance;
    private AppSharedPreferences appSharedPreferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.appSharedPreferences = new AppSharedPreferences(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setLoginStatus(boolean isLogin) {
        DataLocalManager.getInstance().appSharedPreferences.putLoginStatus(PREF_LOGIN_STATUS, isLogin);
    }

    public static boolean getLoginStatus() {
        return DataLocalManager.getInstance().appSharedPreferences.getLoginStatus(PREF_LOGIN_STATUS);
    }

    public static void setAccount(AppAccount account) {
        Gson gson = new Gson();
        String strJsonAccount = gson.toJson(account);
        DataLocalManager.getInstance().appSharedPreferences.putAccount(PREF_ACCOUNT, strJsonAccount);
    }

    public static AppAccount getAccount() {
        String strJsonAccount = DataLocalManager.getInstance().appSharedPreferences.getAccount(PREF_ACCOUNT);
        Gson gson = new Gson();
        AppAccount account = gson.fromJson(strJsonAccount, AppAccount.class);
        return account;
    }
}
