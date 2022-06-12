package com.thaiduong.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.thaiduong.myapplication.R;
import com.thaiduong.myapplication.data_local.DataLocalManager;
import com.thaiduong.myapplication.fragment.LoginFragment;
import com.thaiduong.myapplication.fragment.RegisterFragment;
import com.thaiduong.myapplication.view_pager.DepthPageTransformer;
import com.thaiduong.myapplication.view_pager.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView mNavigationView;
    ViewPager2 mViewPager;
    Fragment loginFragment, registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (DataLocalManager.getLoginStatus()) {
            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
            startActivity(intent);
        } else {
            addViews();

            setUpViewPager();

            mNavigationView.setOnItemSelectedListener(this);
        }

    }

    private void setUpViewPager() {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(MainActivity.this);
        mViewPager.setAdapter(pagerAdapter);

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    mNavigationView.getMenu().findItem(R.id.action_login).setCheckable(true);
                    mNavigationView.getMenu().findItem(R.id.action_register).setCheckable(false);
                }
                if (position == 1) {
                    mNavigationView.getMenu().findItem(R.id.action_register).setCheckable(true);
                    mNavigationView.getMenu().findItem(R.id.action_login).setCheckable(false);
                }
            }
        });
        mViewPager.setPageTransformer(new DepthPageTransformer());
    }

    private void addViews() {
        mNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        mViewPager = (ViewPager2) findViewById(R.id.vew_pager);

//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.vew_pager, new RegisterFragment());
//        fragmentTransaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_login) {
            mViewPager.setCurrentItem(0);
        }
        if(item.getItemId() ==  R.id.action_register) {
            mViewPager.setCurrentItem(1);
        }
        return true;
    }
}