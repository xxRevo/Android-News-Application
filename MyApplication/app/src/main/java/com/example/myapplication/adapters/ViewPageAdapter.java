package com.example.myapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.fragments.first_tab;
import com.example.myapplication.fragments.second_tab;
import com.example.myapplication.fragments.third_tab;

public class ViewPageAdapter extends FragmentStateAdapter {
    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new second_tab();
            case 2:
                return new third_tab();
            default:
                return new first_tab();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
