package com.thaiduong.myapplication.login_register.model;

import android.os.Build;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppAccount {
    private String name;
    private String account;
    private String passwd;
    private String birthday;
    private String gender;
    private final boolean[] validate;

    @NonNull
    @Override
    public String toString() {
        String result = "";
        result += "THÔNG TIN TÀI KHOẢN\n";
        result += String.format("Email: %s%nMật khẩu: %s%nHọ tên: %s%nNgày sinh: %s%nGiới tính: %s%n",
                account, passwd, name, birthday, gender);
        return result;
    }

    public AppAccount() {
        validate = new boolean[4];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 10) {
            this.name = "Họ tên phải ít nhất có độ dài 10 ký tự";
        } else if (!name.matches("^[\\p{L} .'-]+$")) {
            this.name = "Họ tên chỉ được gồm các chữ cái";
        } else {
            this.name = name;
            validate[0] = true;
        }
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        if (!Patterns.EMAIL_ADDRESS.matcher(account).matches()) {
            this.account = "Địa chỉ email không hợp lệ";
        } else {
            this.account = account;
            validate[1] = true;
        }
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        if (passwd.length() < 8) {
            this.passwd = "Mật khẩu phải có ít nhất 8 ký tự";
        } else if (passwd.matches("[^A-Z]+$")) {
            this.passwd = "Mật khẩu phải có ít nhất một chữ in hoa";
        } else if (passwd.matches("[^\\d]+$")) {
            this.passwd = "Mật khẩu phải có ít nhất một chữ số";
        } else {
            this.passwd = passwd;
            validate[2] = true;
        }
    }

    public String getBirthday() {
        return birthday;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setBirthday(String birthday) {
        if (birthday.equals("Chọn ngày sinh")) {
            this.birthday = "Ngày sinh không được để trống";
        } else {
            String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            int birthdayYear = Integer.parseInt(birthday.split("/")[2]);
            int nowYear = Integer.parseInt(localDate.split("/")[2]);
            if (birthdayYear > nowYear - 10) {
                this.birthday = "Ngày sinh không hợp lệ";
            } else {
                this.birthday = birthday;
                validate[3] = true;
            }
        }
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean[] getValidate() {
        return validate;
    }
}
