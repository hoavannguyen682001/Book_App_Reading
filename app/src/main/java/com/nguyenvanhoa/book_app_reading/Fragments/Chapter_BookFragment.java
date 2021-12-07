package com.nguyenvanhoa.book_app_reading.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.nguyenvanhoa.book_app_reading.Activity.User.Content_Story.activity_content_story;
import com.nguyenvanhoa.book_app_reading.Activity.User.Content_Story.activity_content_story2;
import com.nguyenvanhoa.book_app_reading.Activity.User.Content_Story.activity_content_story3;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = listView.getItemAtPosition(i).toString();
                Intent intent;
                switch (item){
                    case "1.Chap 1":
                        intent = new Intent(getActivity(), activity_content_story.class);
                        intent.putExtra("key",item);
                        startActivity(intent);
                        break;
                    case "2.Chap 2":
                        intent = new Intent(getActivity(), activity_content_story2.class);
                        intent.putExtra("key",item);
                        startActivity(intent);
                        break;
                    case "3.Chap 3":
                        intent = new Intent(getActivity(), activity_content_story2.class);
                        intent.putExtra("key",item);
                        startActivity(intent);
                        break;
                    case "4.Chap 4":
                        intent = new Intent(getActivity(), activity_content_story3.class);
                        intent.putExtra("key",item);
                        startActivity(intent);
                        break;
                }



            }
        });

        return view;
    }

}