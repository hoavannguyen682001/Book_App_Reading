package com.nguyenvanhoa.book_app_reading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private TextView btnLogin;
    private EditText txtEmail, txtFullName, txtPassword, txtGender;
    private LinearLayout lnF_G, lnLogin;
    private Button btnSignUp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AnhXa();
        Animation();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa(){
        btnLogin = findViewById(R.id.txtLogin);
        txtEmail = findViewById(R.id.txtEmail_SignUp);
        txtFullName = findViewById(R.id.txtFullName_SignUp);
        txtPassword = findViewById(R.id.txtPassword_SignUp);
        txtGender = findViewById(R.id.txtGender_SignUp);
        lnF_G = findViewById(R.id.lnName_Gender);
        btnSignUp = findViewById(R.id.btnSignUp_SignUpAT);
        lnLogin = findViewById(R.id.lnLogIn);
    }

    private void Animation(){
        txtEmail.setTranslationX(1000);
        txtFullName.setTranslationX(1000);
        lnF_G.setTranslationX(1000);
        txtPassword.setTranslationX(1000);
        btnSignUp.setTranslationX(1000);
        lnLogin.setTranslationX(1000);

        txtEmail.setAlpha(0);
        txtFullName.setAlpha(0);
        lnF_G.setAlpha(0);
        txtPassword.setAlpha(0);
        btnSignUp.setAlpha(0);
        lnLogin.setAlpha(0);

        txtEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        txtFullName.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        lnF_G.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        txtPassword.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        btnSignUp.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1200).start();
        lnLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1400).start();
    }
}
