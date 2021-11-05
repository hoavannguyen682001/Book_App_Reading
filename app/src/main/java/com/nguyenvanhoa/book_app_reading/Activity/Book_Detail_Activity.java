package com.nguyenvanhoa.book_app_reading.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.nguyenvanhoa.book_app_reading.Activity.Content_Story.activity_content_story;
import com.nguyenvanhoa.book_app_reading.Adapter.TabDetailAdapter;
import com.nguyenvanhoa.book_app_reading.Fragments.Chapter_BookFragment;
import com.nguyenvanhoa.book_app_reading.R;

public class Book_Detail_Activity extends AppCompatActivity {
    private CheckBox fav;
    private ImageButton btnBack;
    private ImageView imgbook;
    private TextView namebook;
    private String mName;
    private int mImg;
    private TabLayout mTabLayout;
    private ViewPager mViewPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);



        imgbook = findViewById(R.id.img_book_item);
        namebook = findViewById(R.id.txtName_book);
        fav = findViewById(R.id.cbfavorite);
        btnBack = findViewById(R.id.btnBack);
        mName = getIntent().getStringExtra("bookName");
        mImg = getIntent().getExtras().getInt("bookImg");
        namebook.setText(mName);
        Glide.with(this).load(mImg).into(imgbook);

        mTabLayout = findViewById(R.id.tabLayout);
        mViewPaper = findViewById(R.id.view_tabDetail);

        TabDetailAdapter tabDetailAdapter = new TabDetailAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPaper.setAdapter(tabDetailAdapter);
        mTabLayout.setupWithViewPager(mViewPaper);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Book_Detail_Activity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav.isChecked()){
                    Toast.makeText(Book_Detail_Activity.this, "Add successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Book_Detail_Activity.this, "Remove successfully!", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}