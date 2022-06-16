package com.thaiduong.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.thaiduong.myapplication.R;
import com.thaiduong.myapplication.data_local.DataLocalManager;
import com.thaiduong.myapplication.login_register.model.AppAccount;

public class AccountActivity extends AppCompatActivity {

    long backPressedTime;
    Toast mToast;

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

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            mToast.cancel();
            finishAffinity();
            return;
        } else {
            mToast = Toast.makeText(AccountActivity.this,
                    "Please back again to exit the application",
                    Toast.LENGTH_LONG);
            mToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}