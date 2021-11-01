package com.nguyenvanhoa.book_app_reading.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenvanhoa.book_app_reading.Fragments.ArchivedTabFragment;
import com.nguyenvanhoa.book_app_reading.Fragments.ReadinglistTabFragment;
import com.nguyenvanhoa.book_app_reading.Fragments.VoteTabFragment;


public class LibraryAdapter extends FragmentStatePagerAdapter {
    public LibraryAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ArchivedTabFragment archivedFragment = new ArchivedTabFragment();
                return archivedFragment;
            case 1:
                ReadinglistTabFragment readinglistFragment = new ReadinglistTabFragment();
                return readinglistFragment;
            case 2:
                VoteTabFragment voteFragment = new VoteTabFragment();
                return voteFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Archived";
                break;
            case 1:
                title = "Reading List";
                break;
            case 2:
                title = "Vote";
                break;
            default:
                return null;
        }
        return title;
    }
}
