package com.nguyenvanhoa.book_app_reading.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
    public static String nameClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        nameClass = getLocalClassName();
//        Toast.makeText(getApplication(), nameClass, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(getApplication(), nameClass, Toast.LENGTH_SHORT).show();
                Intent intent;
                if(nameClass.endsWith("HomeActivity")){
                    intent = new Intent(getApplication(), HomeActivity.class);
                    startActivity(intent);
                    return;
                }
                if(nameClass.endsWith("BookshelfActivity")){
                    intent = new Intent(getApplication(), BookshelfActivity.class);
                    startActivity(intent);
                    return;
                }
                if(nameClass.endsWith("LibraryActivity")){
                    intent = new Intent(getApplication(), LibraryActivity.class);
                    startActivity(intent);
                    return;
                }

                if(nameClass.endsWith("SearchActivity")){
                    intent = new Intent(getApplication(), SearchActivity.class);
                    startActivity(intent);
                    return;
                }

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