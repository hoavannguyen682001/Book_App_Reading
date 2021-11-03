package com.nguyenvanhoa.book_app_reading.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nguyenvanhoa.book_app_reading.R;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    private ImageButton btnlogout;
    private CheckBox editname, editphone, editemail, gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnlogout = findViewById(R.id.btnlogout);
        editemail = findViewById(R.id.edit_email);
//        editname = findViewById(R.id.cbEdit_name);
        editphone = findViewById(R.id.edit_phone);
        gender = findViewById(R.id.edit_gender);
        Navigation_bar();
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    public void Navigation_bar(){
        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.nav_person);
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
                        intent = new Intent(getApplicationContext(), BookshelfActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                        break;

                    case R.id.nav_library:
                        intent = new Intent(getApplicationContext(), LibraryActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                        break;
                    case R.id.nav_person:

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
