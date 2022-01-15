package com.nguyenvanhoa.book_app_reading.User.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Admin.Activity.BookViewActivity;
import com.nguyenvanhoa.book_app_reading.Admin.Activity.PdfListActivity;
import com.nguyenvanhoa.book_app_reading.Admin.Adapter.AdapterPdf;
import com.nguyenvanhoa.book_app_reading.Admin.Models.PdfModel;
import com.nguyenvanhoa.book_app_reading.User.Activity.Content_Story.activity_content_story;
import com.nguyenvanhoa.book_app_reading.User.Activity.Content_Story.activity_content_story2;
import com.nguyenvanhoa.book_app_reading.User.Activity.Content_Story.activity_content_story3;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.Adapter.ChapterAdapter;
import com.nguyenvanhoa.book_app_reading.User.Model.Chapter;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class Chapter_BookFragment extends Fragment{
    private ListView listView;
    private ArrayList<Chapter> chapterList;
    private String bookId;
    public Chapter_BookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapter__book, container, false);
        listView = view.findViewById(R.id.lvChapter);

        Intent intent = getActivity().getIntent();
        bookId = intent.getStringExtra("bookId");

        loadChapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String chapter = chapterList.get(i).getId();
                Intent intent = new Intent(getActivity(), BookViewActivity.class);
                intent.putExtra("bookId",bookId);
                intent.putExtra("chapter",chapter);
                startActivity(intent);
            }
        });

        return view;
    }

    public void loadChapter(){
        chapterList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books/"+bookId+"/chapter");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i= 0;
                chapterList.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    Chapter model = ds.getValue(Chapter.class);
                    chapterList.add(model);
                    i++;
                }
//                        Toast.makeText(getContext(), ""+chapterList.get(0).getId(), Toast.LENGTH_SHORT).show();
//                        adapterPdf = new AdapterPdf(PdfListActivity.this, pdfArrayList);
//                        binding.booksRv.setAdapter(adapterPdf);
                ChapterAdapter adapter = new ChapterAdapter(chapterList,getActivity(),R.layout.row_list_chapter);
                listView.setAdapter( adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "load chapter fail "+ error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

}