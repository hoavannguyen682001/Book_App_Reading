package com.nguyenvanhoa.book_app_reading.Activity.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenvanhoa.book_app_reading.R;

public class ForgotPassActivity extends Activity {

    private TextView tvExit, tvSignUp;
    private Button btnSendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tvExit = findViewById(R.id.tvExit);
        tvSignUp = findViewById(R.id.tvSignUp);
        btnSendCode = findViewById(R.id.btnSendCode);

        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplication(), LoginActivity.class);
                startActivity(intent);
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplication(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Check Your Email", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
