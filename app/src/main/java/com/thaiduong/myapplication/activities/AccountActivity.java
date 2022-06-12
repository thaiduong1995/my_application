package com.thaiduong.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.thaiduong.myapplication.R;
import com.thaiduong.myapplication.data_local.DataLocalManager;
import com.thaiduong.myapplication.model.AppAccount;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        TextView textView = findViewById(R.id.text_view);

        AppAccount account = DataLocalManager.getAccount();
        if (account != null) {
            textView.setText(account.toString());
        }
    }
}