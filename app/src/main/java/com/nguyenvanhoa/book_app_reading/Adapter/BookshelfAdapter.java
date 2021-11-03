package com.nguyenvanhoa.book_app_reading.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenvanhoa.book_app_reading.Fragments.ClassicTabFragment;
import com.nguyenvanhoa.book_app_reading.Fragments.NewTabFragment;
import com.nguyenvanhoa.book_app_reading.Fragments.PopularTabFragment;
import com.nguyenvanhoa.book_app_reading.Fragments.ReadingTabFragment;


public class BookshelfAdapter extends FragmentStatePagerAdapter {
    public BookshelfAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PopularTabFragment popularFragment = new PopularTabFragment();
                return popularFragment;
            case 1:
                NewTabFragment newFragment = new NewTabFragment();
                return newFragment;
            case 2:
                ClassicTabFragment classicFragment = new ClassicTabFragment();
                return classicFragment;
            case 3:
                ReadingTabFragment readingFragment = new ReadingTabFragment();
                return readingFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Popular";
                break;
            case 1:
                title = "New";
                break;
            case 2:
                title = "Classic";
                break;
            case 3:
                title = "Reading";
                break;
            default:
                return null;
        }
        return title;
    }
}