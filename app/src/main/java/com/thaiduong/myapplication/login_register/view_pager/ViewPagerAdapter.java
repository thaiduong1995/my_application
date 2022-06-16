package com.thaiduong.myapplication.login_register.view_pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.thaiduong.myapplication.login_register.fragment.LoginFragment;
import com.thaiduong.myapplication.login_register.fragment.RegisterFragment;

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
