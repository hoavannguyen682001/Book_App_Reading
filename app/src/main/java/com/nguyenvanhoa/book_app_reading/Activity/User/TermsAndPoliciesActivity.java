package com.nguyenvanhoa.book_app_reading.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenvanhoa.book_app_reading.R;

public class TermsAndPoliciesActivity extends AppCompatActivity {
    ImageView imgBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imgBack = findViewById(R.id.btnBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
//        btnAboutUs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
//                startActivity(intent);
//            }
//        });
//        btnterm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), TermsAndPoliciesActivity.class);
//                startActivity(intent);
//            }
//        });
//        btnlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void setEditClick(EditText input, CheckBox checkBox){
//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (checkBox.isChecked()){
//                    input.setEnabled(true);
//                }else{
//                    input.setEnabled(false);
//                    input.setText(input.getText());
//                    Toast.makeText(getApplication(), "Edit Successfully!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
    }
}
