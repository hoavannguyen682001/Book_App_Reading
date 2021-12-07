package com.nguyenvanhoa.book_app_reading.Activity.User.Content_Story;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.nguyenvanhoa.book_app_reading.Activity.User.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.R;

public class activity_content_story3 extends AppCompatActivity {

    PDFView pdfView;
    ImageButton chapter_back,back_to_detail;
    TextView txtChapter;
    String chapterIndex;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Book_Detail_Activity.class);
        intent.putExtra("bookName","Sycamore Tree");
        intent.putExtra("bookImg",R.drawable.sycamorerow);
        startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_story3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        pdfView = (PDFView) findViewById(R.id.pdfView);
        txtChapter = (TextView) findViewById(R.id.txtChapter);
        chapter_back = (ImageButton) findViewById(R.id.chapter_back);
        back_to_detail = (ImageButton) findViewById(R.id.back_to_detail);

        txtChapter.setText("Chapter 4 : Win");
        pdfView.fromAsset("content_story.pdf").load();

        chapterIndex = getIntent().getStringExtra("key");

        chapter_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterIndex = "3.Chap 3";
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
                intent.putExtra("bookName","Sycamore Tree");
                intent.putExtra("bookImg",R.drawable.sycamorerow);
                startActivity(intent);
            }
        });
    }
}

