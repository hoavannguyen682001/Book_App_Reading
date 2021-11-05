package com.nguyenvanhoa.book_app_reading.Activity.Content_Story;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.nguyenvanhoa.book_app_reading.R;

public class activity_content_story2 extends AppCompatActivity {

    PDFView pdfView;
    ImageButton chapter_next,chapter_back;
    TextView txtChapter;
    String chapterIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_story2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pdfView = (PDFView) findViewById(R.id.pdfView);

        chapter_back = (ImageButton) findViewById(R.id.chapter_back);
        chapter_next = (ImageButton) findViewById(R.id.chapter_next);
        txtChapter = (TextView) findViewById(R.id.txtChapter);

        chapterIndex = getIntent().getStringExtra("key");

        if(chapterIndex.equals("2.Chap 2")){
            txtChapter.setText("Chapter 2 : Power");
            pdfView.fromAsset("content_story.pdf").load();
        }

        if(chapterIndex.equals("3.Chap 3")){
            txtChapter.setText("Chapter 3 : Quality");
            pdfView.fromAsset("content_story.pdf").load();
        }

        chapter_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(chapterIndex.equals("2.Chap 2")){
                    chapterIndex = "1.Chap 1";
                    Intent intent = new Intent(getApplicationContext(),activity_content_story.class);
                    intent.putExtra("key",chapterIndex);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }else if (chapterIndex.equals("3.Chap 3")){
                        chapterIndex = "2.Chap 2";
                        txtChapter.setText("Chapter 2 : Power");
                        pdfView.fromAsset("content_story.pdf").load();
                    }
            }
        });
        chapter_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(chapterIndex.equals("2.Chap 2")){
                    chapterIndex = "3.Chap 3";
                    txtChapter.setText("Chapter 3 : Quality");
                    pdfView.fromAsset("content_story.pdf").load();
                }else if(chapterIndex.equals("3.Chap 3")){
                    chapterIndex = "4.Chap 4";
                    Intent intent = new Intent(getApplicationContext(),activity_content_story3.class);
                    intent.putExtra("key",chapterIndex);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }

            }
        });

    }
}
