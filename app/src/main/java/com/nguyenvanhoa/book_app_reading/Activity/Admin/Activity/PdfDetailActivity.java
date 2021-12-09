package com.nguyenvanhoa.book_app_reading.Activity.Admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Models.MyApplication;
import com.nguyenvanhoa.book_app_reading.Activity.User.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.databinding.ActivityPdfDetailBinding;

public class PdfDetailActivity extends AppCompatActivity {
    ActivityPdfDetailBinding binding;
    String bookId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfDetailBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(binding.getRoot());
        Intent i = getIntent();
        bookId = i.getStringExtra("bookId");
        loadBookDetail();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        MyApplication.incrementBookViewCount(bookId);

        binding.readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), BookViewActivity.class);
                i.putExtra("bookId", bookId);
                startActivity(i);
            }
        });
    }

    private void loadBookDetail() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.child(bookId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String title = ""+snapshot.child("title").getValue();
                        String description = ""+snapshot.child("description").getValue();
                        String categoryId = ""+snapshot.child("categoryId").getValue();
                        String url = ""+snapshot.child("url").getValue();
                        String timestamp = ""+snapshot.child("timestamp").getValue();

                        String date = MyApplication.formatTimestamp(Long.parseLong(timestamp));
                        MyApplication.loadCategory(binding.categoryTv, ""+categoryId );

                        MyApplication.loadPdfFromUrlPage(""+url, ""+title, binding.pdfView, getApplication(), binding.progressBar);
                        binding.titleTv.setText(title);
                        binding.descriptionTv.setText(description);
                        binding.dateTv.setText(date);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}