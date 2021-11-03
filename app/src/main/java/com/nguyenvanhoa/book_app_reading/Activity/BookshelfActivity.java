package com.nguyenvanhoa.book_app_reading.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.nguyenvanhoa.book_app_reading.Adapter.BookshelfAdapter;
import com.nguyenvanhoa.book_app_reading.R;

public class BookshelfActivity extends AppCompatActivity {
    TextView txtLibrary;
    TabLayout tabLayout;
    ViewPager viewPager;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf);

        AnhXa();

        BookshelfAdapter adapter =new BookshelfAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setAllCaps(tabLayout,false);

        Navigation_bar();
    }
    public void AnhXa(){
        txtLibrary = findViewById(R.id.txtlibrary);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpaper);
    }
    public static void setAllCaps(View view, boolean caps) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++)
                setAllCaps(((ViewGroup) view).getChildAt(i),caps);
        } else if (view instanceof TextView) ((TextView) view).setAllCaps(caps);
    }

    public void Navigation_bar(){
        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.nav_bookshelf);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Intent intent;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                        break;
                    case R.id.nav_bookshelf:
                        break;
                    case R.id.nav_library:
                        intent = new Intent(getApplicationContext(), LibraryActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                        break;
                    case R.id.nav_person:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                        break;
                    case R.id.nav_search:
                        intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                        break;
                }
                return false;
            }
        });
    }
}
