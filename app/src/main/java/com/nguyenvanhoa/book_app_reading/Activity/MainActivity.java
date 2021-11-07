package com.nguyenvanhoa.book_app_reading.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.nguyenvanhoa.book_app_reading.R;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin, btnSignUp;
    private TextView tvSkip;
    private int x = 1000, alpha = 0, delay = 200;
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
        tvSkip = findViewById(R.id.tvSkip);
    }

    private void Animation(){
        btnLogin.setTranslationX(x);
        btnSignUp.setTranslationX(x);
        tvSkip.setTranslationX(x);

        btnLogin.setAlpha(alpha);
        btnSignUp.setAlpha(alpha);
        tvSkip.setAlpha(alpha);

        btnLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(delay).start();
        btnSignUp.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(delay+100).start();
        tvSkip.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(delay+200).start();
    }

    private void onClick(){
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), HomeActivity.class);
                startActivity(intent);
            }
        });
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