package com.thaiduong.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingEngine;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnChangeListener;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;
import com.thaiduong.myapplication.R;
import com.ramotion.paperonboarding.R.*;
import com.thaiduong.myapplication.data_local.DataLocalManager;

import java.util.ArrayList;

public class OnBrandingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ramotion.paperonboarding.R.layout.onboarding_main_layout);

        if (DataLocalManager.getLoginStatus()) {
            Intent intent = new Intent(OnBrandingScreenActivity.this, AccountActivity.class);
            startActivity(intent);
        } else {
            getPaperOnboardingScreen();
        }
    }

    private void getPaperOnboardingScreen() {
        PaperOnboardingEngine engine = new PaperOnboardingEngine(
                findViewById(com.ramotion.paperonboarding.R.id.onboardingRootView),
                getPaperOnboardingPageData(), this);

        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                Intent intent = new Intent(OnBrandingScreenActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<PaperOnboardingPage> getPaperOnboardingPageData() {
        PaperOnboardingPage scr1 = new PaperOnboardingPage("Đăng nhập",
                "Đăng nhập email và tài khoản của bạn để sử dụng ứng dụng",
                Color.parseColor("#678FB4"), R.drawable.ic_login_screen_big, R.drawable.ic_login_sceen_small);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Đăng ký",
                "Điền đầy đủ các thông tin của bạn để đăng ký tài khoản",
                Color.parseColor("#65B0B4"), R.drawable.ic_register_screen_big, R.drawable.ic_register_screen_small);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Bộ sưu tập",
                "Nơi bạn lưu trữ bộ sưu tập của mình",
                Color.parseColor("#9B90BC"), R.drawable.ic_collection_screen_big, R.drawable.ic_collection_screen_small);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);

        return elements;
    }
}