package com.nguyenvanhoa.book_app_reading.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Activity.DashBoardActivity;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Models.MyApplication;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    private ActivityProfileBinding binding;
    public static String userType;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());
        enableDashboard();
        initView();
        setOnClick();
        Navigation_bar();
        firebaseAuth = FirebaseAuth.getInstance();
        loadUserInfo();
    }

    private void loadUserInfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.child(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Toast.makeText(getApplication(), ""+firebaseAuth.getUid(), Toast.LENGTH_SHORT).show();
                        String email = ""+snapshot.child("email").getValue();
                        Toast.makeText(getApplication(), ""+email, Toast.LENGTH_SHORT).show();

//                        String fullName = ""+snapshot.child("fullName").getValue();
//                        String profileImage = ""+snapshot.child("profileImage").getValue();
//                        String timestamp = ""+snapshot.child("timestamp").getValue();
//                        String uid = ""+snapshot.child("uid").getValue();
//                        String formatDate = MyApplication.formatTimestamp(Long.parseLong(timestamp));
//                        binding.nameEt.setText(fullName);
//                        binding.emailEt.setText(email);
//                        binding.nameTv.setText(fullName);
//                        Glide.with(ProfileActivity.this)
//                                .load(profileImage)
//                                .placeholder(R.drawable.ic_person)
//                                .into(binding.profileIv);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void enableDashboard(){
        if(userType.equals("admin")){
            binding.dashboardBtn.setVisibility(View.VISIBLE);
        }
    }

    public void initView(){
        setEditClick(binding.nameEt, binding.cbEditName);
        setEditClick(binding.emailEt, binding.cbEditEmail);
    }

    private void setEditClick(EditText input, CheckBox checkBox){
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    input.setEnabled(true);
                }else{
                    input.setEnabled(false);
                    input.setText(input.getText());
                    Toast.makeText(getApplication(), "Edit Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setOnClick(){
        binding.btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        binding.termBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TermsAndPoliciesActivity.class);
                startActivity(intent);
            }
        });
        binding.btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        binding.dashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), DashBoardActivity.class);
                startActivity(i);
                binding.dashboardBtn.setVisibility(View.INVISIBLE);
                finish();
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
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_bookshelf:
                        intent = new Intent(getApplicationContext(), BookshelfActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_library:
                        intent = new Intent(getApplicationContext(), LibraryActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_person:

                        break;
                    case R.id.nav_search:
                        intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });
    }
}
