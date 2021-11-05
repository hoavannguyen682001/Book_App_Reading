package com.nguyenvanhoa.book_app_reading.Activity.Content_Story;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.nguyenvanhoa.book_app_reading.Activity.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.Fragments.Chapter_BookFragment;
import com.nguyenvanhoa.book_app_reading.R;

public class activity_content_story extends FragmentActivity {

    PDFView pdfView;
    ImageButton chapter_next,back_to_detail;
    String chapterIndex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_story);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pdfView = (PDFView) findViewById(R.id.pdfView);

        chapterIndex = getIntent().getStringExtra("key");
        pdfView.fromAsset("content_story.pdf").load();

        chapter_next = (ImageButton) findViewById(R.id.chapter_next);
        back_to_detail = (ImageButton) findViewById(R.id.back_to_detail) ;

        chapter_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterIndex = "2.Chap 2";
                Intent intent = new Intent(getApplicationContext(),activity_content_story2.class);
                intent.putExtra("key",chapterIndex);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        back_to_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Book_Detail_Activity.class);
                startActivity(intent);
            }
        });
    }


}

