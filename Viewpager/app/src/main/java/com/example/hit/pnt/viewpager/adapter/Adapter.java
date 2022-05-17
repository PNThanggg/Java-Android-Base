package com.example.hit.pnt.viewpager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.hit.pnt.viewpager.fragment.CartFragment;
import com.example.hit.pnt.viewpager.fragment.HomeFragment;
import com.example.hit.pnt.viewpager.fragment.NotificationFragment;
import com.example.hit.pnt.viewpager.fragment.PersonFragment;

public class Adapter extends FragmentStatePagerAdapter {
    public Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new CartFragment();

            case 2:
                return new NotificationFragment();

            case 3:
                return new PersonFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
