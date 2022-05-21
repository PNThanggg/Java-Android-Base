package com.example.hit.pnt.bottomappbar.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.hit.pnt.bottomappbar.fragment.AccountFragment;
import com.example.hit.pnt.bottomappbar.fragment.ExitFragment;
import com.example.hit.pnt.bottomappbar.fragment.HelpFragment;
import com.example.hit.pnt.bottomappbar.fragment.HomeFragment;

public class MenuAdapter extends FragmentStatePagerAdapter {
    public MenuAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new AccountFragment();

            case 2:
                return new HelpFragment();

            case 3:
                return new ExitFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
