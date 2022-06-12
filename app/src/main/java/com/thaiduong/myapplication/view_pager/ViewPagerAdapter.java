package com.thaiduong.myapplication.view_pager;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.thaiduong.myapplication.activities.MainActivity;
import com.thaiduong.myapplication.fragment.LoginFragment;
import com.thaiduong.myapplication.fragment.RegisterFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new Fragment();
        switch (position) {
            case 0:
                fragment = LoginFragment.newInstance();
                break;
            case 1:
                fragment = RegisterFragment.newInstance();
                break;
            default:
                break;
        }
        return fragment;

    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
