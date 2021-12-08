package com.nguyenvanhoa.book_app_reading.Activity.Admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoa.book_app_reading.Activity.User.HomeActivity;
import com.nguyenvanhoa.book_app_reading.Activity.User.MainActivity;
import com.nguyenvanhoa.book_app_reading.Activity.User.ProfileActivity;
import com.nguyenvanhoa.book_app_reading.databinding.ActivityDashBoardBinding;
import com.nguyenvanhoa.book_app_reading.databinding.ActivityProfileBinding;

import java.util.HashMap;

public class DashBoardActivity extends AppCompatActivity {

    private ActivityDashBoardBinding binding;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_dash_board);

        firebaseAuth = FirebaseAuth.getInstance();
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        binding.ShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), CategoriesActivity.class);
                startActivity(i);
            }
        });

        binding.AddBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), PdfAddActivity.class);
                startActivity(i);
            }
        });

        binding.userActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity.userType = "admin";
                Intent i = new Intent(getApplication(), HomeActivity.class);
//                userType = getIntent().getStringExtra("userType");
//                i.putExtra("userType", userType);
                startActivity(i);
                finish();
            }
        });

        binding.logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private String category = "";
    private void validateData() {
        category = binding.categoryEt.getText().toString().trim();
        if(TextUtils.isEmpty(category)){
            Toast.makeText(getApplication(), "enter your category", Toast.LENGTH_SHORT).show();

        }else{
            addCategoryFirebase();
        }
    }
    private void addCategoryFirebase() {
        long times = System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id",""+times);
        hashMap.put("category", ""+category);
        hashMap.put("timestamp",times);
        hashMap.put("uid", ""+firebaseAuth.getUid());
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
        ref.child(""+times)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplication(), "Success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplication(), "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}