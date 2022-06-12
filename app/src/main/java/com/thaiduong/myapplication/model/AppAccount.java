package com.thaiduong.myapplication.model;

import androidx.annotation.NonNull;

public class AppAccount {
    private String name;
    private String account;
    private String passwd;
    private String birthday;
    private String gender;

    @NonNull
    @Override
    public String toString() {
        String result = "";
        result += "THÔNG TIN TÀI KHOẢN\n";
        result += String.format("Email: %s%nMật khẩu: %s%nHọ tên: %s%nNgày sinh: %s%nGiới tính: %s%n",
                account, passwd, name, birthday, gender);
        return result;
    }

    public AppAccount(String name, String account, String passwd, String birthday, String gender) {
        this.name = name;
        this.account = account;
        this.passwd = passwd;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
