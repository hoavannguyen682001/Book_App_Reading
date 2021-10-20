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

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtEmail, txtPassword;
    private TextView txtSignUp;
    private LinearLayout ln;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activivy_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AnhXa();
        Animation();

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AnhXa(){
        btnLogin = findViewById(R.id.btnLogin_LoginAT);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        ln = findViewById(R.id.lnSignUp);
        txtSignUp = findViewById(R.id.txtSignUp);
    }

    private void Animation(){
        txtEmail.setTranslationX(1000);
        txtPassword.setTranslationX(1000);
        btnLogin.setTranslationX(1000);
        ln.setTranslationX(1000);

        txtEmail.setAlpha(0);
        txtPassword.setAlpha(0);
        btnLogin.setAlpha(0);
        ln.setAlpha(0);

        txtEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        txtPassword.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        btnLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        ln.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
    }
}
