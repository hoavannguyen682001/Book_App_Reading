package com.nguyenvanhoa.book_app_reading.Activity.Content_Story;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.nguyenvanhoa.book_app_reading.R;

public class activity_content_story extends AppCompatActivity {

    PDFView pdfView;
    ImageButton chapter_next;
    int index = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_story);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("content_story.pdf").load();

        chapter_next = (ImageButton) findViewById(R.id.chapter_next);

        chapter_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                Intent intent = new Intent(getApplicationContext(),activity_content_story2.class);
                intent.putExtra("index",index);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }
}

