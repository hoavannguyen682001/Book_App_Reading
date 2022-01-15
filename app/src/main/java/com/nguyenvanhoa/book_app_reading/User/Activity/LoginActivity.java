package com.nguyenvanhoa.book_app_reading.User.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Admin.Activity.DashBoardActivity;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtEmail, txtPassword;
    private TextView txtSignUp;
    private LinearLayout ln;
    private TextView tvforgotpass;
    private ImageButton btnBack;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    private static final int REQUEST_PERMISSION = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activivy_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestPermission();
        AnhXa();
        Animation();
        setClick();

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int hasWritePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int hasReadPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            List<String> permissions = new ArrayList<String>();
            if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            } else {
//              preferencesUtility.setString("storage", "true");
            }

            if (hasReadPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

            } else {
//              preferencesUtility.setString("storage", "true");
            }

            if (!permissions.isEmpty()) {
//              requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_CODE_SOME_FEATURES_PERMISSIONS);

                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE},
                        REQUEST_PERMISSION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        System.out.println("Permissions --> " + "Permission Granted: " + permissions[i]);
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED){
                        System.out.println("Permissions --> " + "Permission Denied: " + permissions[i]);
                    }
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
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
                validateData();
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

    private String email = "", password = "";
    private void validateData() {
        // get data
        email = txtEmail.getText().toString().trim();
        password = txtPassword.getText().toString().trim();

        // validate data
        if(TextUtils.isEmpty(email)){
            Toast.makeText(LoginActivity.this, "Enter your email...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Enter password...", Toast.LENGTH_SHORT).show();
        }else{
            loginUser();
        }
    }

    private void loginUser() {
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        checkUser();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Email or password is not correctly...\nPlease try again!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkUser() {
//        progressDialog.setMessage("Checking user type...");
//        progressDialog.show();
        // get current user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        //check in db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        //check user type
                        String userType = "" + snapshot.child("userType").getValue();

                        if(userType.equals("user")){
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            ProfileActivity.userType = "user";
                            finish();
                        }else if(userType.equals("admin")){
                            //hien thi form admin
                            Intent i = new Intent(getApplicationContext(), DashBoardActivity.class);
                            ProfileActivity.userType = "admin";
                            startActivity(i);
                            finish();
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
    }
}
