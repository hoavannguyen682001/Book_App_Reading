package com.nguyenvanhoa.book_app_reading.Admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Admin.Models.MyApplication;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.Adapter.TabDetailAdapter;
import com.nguyenvanhoa.book_app_reading.databinding.ActivityPdfDetailBinding;

public class PdfDetailActivity extends AppCompatActivity {
    ActivityPdfDetailBinding binding;
    String bookId;

    boolean isInMyFavorite = false;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfDetailBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(binding.getRoot());
        Intent i = getIntent();
        bookId = i.getStringExtra("bookId");

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null){
            checkIsFavorite();
        }
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
                i.putExtra("chapter","1");
                MyApplication.addToReading( PdfDetailActivity.this, bookId);
                startActivity(i);
            }
        });

        binding.addFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseAuth.getCurrentUser() != null){
                    if(isInMyFavorite){
                        MyApplication.removeFromFavorite(PdfDetailActivity.this, bookId);
                    }else{
                        MyApplication.addToFavorite(PdfDetailActivity.this, bookId);
                    }

                }
            }
        });
        TabDetailAdapter tabDetailAdapter = new TabDetailAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewTabDetail.setAdapter(tabDetailAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewTabDetail);
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
                        String author = ""+snapshot.child("author").getValue();
                        String date = MyApplication.formatTimestamp(Long.parseLong(timestamp));
                        MyApplication.loadCategory(binding.categoryTv, ""+categoryId );

                        MyApplication.loadPdfFromUrlPage(""+url, ""+title, binding.pdfView, getApplication(), binding.progressBar);
                        binding.titleTv.setText(title);
//                        binding.descriptionTv.setText(description);
                        binding.dateTv.setText(date);
                        binding.authorTv.setText(author);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void checkIsFavorite(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid()).child("Favorites").child(bookId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        isInMyFavorite = snapshot.exists();
                        if (isInMyFavorite){ //true-> book exist in your farovite list
                            binding.addFavBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_star_rate,0,0);
                        }else{
                            binding.addFavBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_star_normal,0,0);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}