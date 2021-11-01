package com.nguyenvanhoa.book_app_reading.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenvanhoa.book_app_reading.Fragments.Chapter_BookFragment;
import com.nguyenvanhoa.book_app_reading.Fragments.Description_BookFragment;


public class TabDetailAdapter extends FragmentStatePagerAdapter {
    public TabDetailAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Description";
                break;
            case 1:
                title = "Chapter";
                break;
            default:
                title = "Description";
        }
        return title;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new Description_BookFragment();
            case 1:
                return new Chapter_BookFragment();
            default:
                return new Description_BookFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
