package com.nguyenvanhoa.book_app_reading.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenvanhoa.book_app_reading.Adapter.LibraryAdapter;
import com.nguyenvanhoa.book_app_reading.R;

public class AboutUsActivity extends AppCompatActivity {

    private ImageButton imgBack;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imgBack = findViewById(R.id.btnBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_library);
//    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    AnhXa();
//    LibraryAdapter adapter =new LibraryAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//    setAllCaps(tabLayout,false);
//    Navigation_bar();
}
