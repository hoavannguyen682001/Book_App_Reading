package com.nguyenvanhoa.book_app_reading.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.nguyenvanhoa.book_app_reading.R;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin, btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        AnhXa();
        Animation();
        onClick();
    }

    private void AnhXa(){
        btnLogin = findViewById(R.id.btn_LogInLauncherAt);
        btnSignUp  = findViewById(R.id.btn_SignUpLauncherAt);
    }

    private void Animation(){
        btnLogin.setTranslationX(1000);
        btnSignUp.setTranslationX(1000);

        btnLogin.setAlpha(0);
        btnSignUp.setAlpha(0);

        btnLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        btnSignUp.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
    }

    private void onClick(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity((intent));
            }
        });
    }
}