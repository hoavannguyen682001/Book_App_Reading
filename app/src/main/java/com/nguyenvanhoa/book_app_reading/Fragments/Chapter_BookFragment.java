package com.nguyenvanhoa.book_app_reading.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.nguyenvanhoa.book_app_reading.Activity.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.Activity.Content_Story.activity_content_story;
import com.nguyenvanhoa.book_app_reading.R;

public class Chapter_BookFragment extends Fragment{
    private ListView listView;

    public Chapter_BookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapter__book, container, false);
        listView = view.findViewById(R.id.lvChapter);
        String[] b = new String[10];
        for (int i = 0; i < 10; i++) {
            b[i] = (i+1)+".Chap " +(i+1);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, b);
        listView.setAdapter( adapter);
        return view;

    }

}