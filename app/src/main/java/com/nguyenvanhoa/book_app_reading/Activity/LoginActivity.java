package com.nguyenvanhoa.book_app_reading.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenvanhoa.book_app_reading.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtEmail, txtPassword;
    private TextView txtSignUp;
    private LinearLayout ln;
    private TextView tvforgotpass;
    private ImageButton btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activivy_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AnhXa();
        Animation();
        setClick();

    }

    public void AnhXa(){
        btnLogin = findViewById(R.id.btnLogin_LoginAT);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        ln = findViewById(R.id.lnSignUp);
        txtSignUp = findViewById(R.id.txtSignUp);
        tvforgotpass = findViewById(R.id.tvForgotPass);
        btnBack = findViewById(R.id.btnBack);
    }

    private void Animation(){
        txtEmail.setTranslationX(800);
        txtPassword.setTranslationX(800);
        btnLogin.setTranslationX(800);
        ln.setTranslationX(800);
        tvforgotpass.setTranslationX(800);

        txtEmail.setAlpha(0);
        txtPassword.setAlpha(0);
        btnLogin.setAlpha(0);
        ln.setAlpha(0);
        tvforgotpass.setAlpha(0);

        txtEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        txtPassword.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        btnLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        tvforgotpass.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        ln.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
    }

    private void setClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        tvforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ForgotPassActivity.class);
                startActivity(intent);
            }
        });
    }
}
