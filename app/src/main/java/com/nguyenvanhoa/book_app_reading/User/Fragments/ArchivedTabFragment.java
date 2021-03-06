package com.nguyenvanhoa.book_app_reading.User.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.book_app_reading.User.Model.Book2;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.RecyclerView_Book.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class ArchivedTabFragment extends Fragment {
    private RecyclerView rvbooks;
    private List<Book2> books;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_archived, container, false);
        rvbooks = root.findViewById(R.id.rcvBook);
        BookAdapter bookAdapter = new BookAdapter(getContext(), books);
        rvbooks.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvbooks.setAdapter(bookAdapter);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        books = new ArrayList<>();
        books.add(new Book2("Themartian", "by John Grisham", "August 19, 2014", "Horror", R.drawable.shaping_school_culture));
        books.add(new Book2("Spider Cover", "by John Grisham", "August 19, 2014", "Horror", R.drawable.school_leaders));
        books.add(new Book2("Sycamore Row", "by John Grisham", "August 19, 2014", "Horror", R.drawable.circe));
        books.add(new Book2("Werewolves", "by John Grisham", "August 19, 2014", "Horror", R.drawable.braiding_sweetgrass));
        books.add(new Book2("GrindelWall Row", "by John Grisham", "August 19, 2014", "Horror", R.drawable.the_story_of_schit_creek));
        books.add(new Book2("Moana", "by John Grisham", "August 19, 2014", "Horror", R.drawable.the_stranger));
    }

}
