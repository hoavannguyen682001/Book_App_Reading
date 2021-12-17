package com.nguyenvanhoa.book_app_reading.User.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private TextView btnLogin;
    private EditText txtEmail, txtFullName, txtPassword;
    private AutoCompleteTextView spnGender;
    private LinearLayout lnF_G, lnLogin;
    private Button btnSignUp;
    private ImageButton btnBack;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AnhXa();
        Animation();
//        InitSpinner();

        //Init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        //Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    // validate data
    private String fullName = "", email ="", password = "";
    private void validateData() {

        //get data
        fullName = txtFullName.getText().toString().trim();
        email = txtEmail.getText().toString().trim();
        password = txtPassword.getText().toString().trim();
        
        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Invalid email address... !", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(fullName)){
            Toast.makeText(this, "Enter your name...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show();
        }else {
            createUserAccount();
        }
    }

    private void createUserAccount() {
        //show progress dialog
        progressDialog.setTitle("Creating account...");
        progressDialog.show();

        //create user in firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        updateUserInfo();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //update user info to db firebase auth
    private void updateUserInfo() {
        progressDialog.setTitle("Saving user info...");

        Long timestamp = System.currentTimeMillis();

        //get uid
        String uid = firebaseAuth.getUid();

        //set up data to add in db
        HashMap<String, Object>  hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("email", email);
        hashMap.put("fullName", fullName);
        hashMap.put("profileImage", "");
        hashMap.put("userType", "user");
        hashMap.put("timestamp", timestamp);

        //set data to db
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
         reference.child(uid)
                 .setValue(hashMap)
                 .addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, "Account created...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                     }
                 })
                 .addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(Exception e) {
                        progressDialog.dismiss();
                         Toast.makeText(SignUpActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 });

    }

    private void AnhXa() {
        btnLogin = findViewById(R.id.txtLogin);
        txtEmail = findViewById(R.id.txtEmail_SignUp);
        txtFullName = findViewById(R.id.txtFullName_SignUp);
        txtPassword = findViewById(R.id.txtPassword_SignUp);
        lnF_G = findViewById(R.id.lnName_Gender);
        btnSignUp = findViewById(R.id.btnSignUp_SignUpAT);
        lnLogin = findViewById(R.id.lnLogIn);
        btnBack = findViewById(R.id.btnBack);
    }

    private void Animation() {
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

//    private void InitSpinner() {
//        ArrayList<String> listGender = new ArrayList<>();
//        listGender.add("Male");
//        listGender.add("Female");
//        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout., listGender);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnGender.setAdapter(adapter);
//        String[] arrGender = new String[]{"Male", "Female", "Other"};
//        ArrayAdapter<CharSequence> GenderAdapter = new ArrayAdapter<CharSequence>(this, R.layout.custom_spinner, arrGender);
//        spnGender.setAdapter(GenderAdapter);
//    }



}
