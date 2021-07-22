package com.toon.estore.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.toon.estore.View.LoginFrag;
import com.toon.estore.View.SignFrag;

public class PageAdapter extends FragmentPagerAdapter {
    int tabCount;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new SignFrag();
            case 1: return new LoginFrag();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
