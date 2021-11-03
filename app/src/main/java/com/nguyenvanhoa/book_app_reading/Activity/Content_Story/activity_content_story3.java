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

public class activity_content_story3 extends AppCompatActivity {

    PDFView pdfView;
    ImageButton chapter_back;
    TextView txtChapter;
    int index ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_story3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        pdfView = (PDFView) findViewById(R.id.pdfView);
        txtChapter = (TextView) findViewById(R.id.txtChapter);
        chapter_back = (ImageButton) findViewById(R.id.chapter_back);

        txtChapter.setText("Chapter 4 : Win");
        pdfView.fromAsset("content_story.pdf").load();
        index = getIntent().getIntExtra("index",0);

        chapter_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                Intent intent = new Intent(getApplicationContext(),activity_content_story2.class);
                intent.putExtra("index",index);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }
}

