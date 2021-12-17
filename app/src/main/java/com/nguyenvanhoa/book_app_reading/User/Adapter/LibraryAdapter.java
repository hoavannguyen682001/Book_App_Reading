package com.nguyenvanhoa.book_app_reading.User.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenvanhoa.book_app_reading.User.Fragments.ReadinglistTabFragment;
import com.nguyenvanhoa.book_app_reading.User.Fragments.VoteTabFragment;


public class LibraryAdapter extends FragmentStatePagerAdapter {
    public LibraryAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ReadinglistTabFragment readinglistFragment = new ReadinglistTabFragment();
                return readinglistFragment;
            case 1:
                VoteTabFragment voteFragment = new VoteTabFragment();
                return voteFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Reading List";
                break;
            case 1:
                title = "Favorite";
                break;
            default:
                return null;
        }
        return title;
    }
}
