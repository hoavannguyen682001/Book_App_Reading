package com.nguyenvanhoa.book_app_reading.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Activity.DashBoardActivity;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    private Button btnlogout, btnterm, btnAboutUs;
    private CheckBox editname, editemail, cbEdit_user;
    private EditText inputname, inputemail, inputUser;
    private ActivityProfileBinding binding;
    public static String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());
        btnlogout = findViewById(R.id.btnlogout);
        Navigation_bar();
        enableDashboard();
        initView();
        setOnClick();
    }

    private void setEditClick(EditText input, CheckBox checkBox){
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    input.setEnabled(true);
                }else{
                    input.setEnabled(false);
                    input.setText(input.getText());
                    Toast.makeText(getApplication(), "Edit Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Navigation_bar(){
        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.nav_person);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Intent intent;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_bookshelf:
                        intent = new Intent(getApplicationContext(), BookshelfActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_library:
                        intent = new Intent(getApplicationContext(), LibraryActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_person:

                        break;
                    case R.id.nav_search:
                        intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });
    }

    public void enableDashboard(){
//        String typeUser = getIntent().getStringExtra("userType");
//        if(!(userType == null)){
            if(userType.equals("admin")){
                binding.dashboardBtn.setVisibility(View.VISIBLE);
//                userType = "";
            }
//        }

    }

    public void initView(){
        inputname = findViewById(R.id.input_name);
        editname = findViewById(R.id.cbEdit_name);
        btnterm = findViewById(R.id.term);
        btnAboutUs = findViewById(R.id.btnAboutUs);

        inputemail = findViewById(R.id.input_email);
        editemail = findViewById(R.id.cbEdit_email);

        inputUser = findViewById(R.id.input_user);
        cbEdit_user = findViewById(R.id.cbEdit_user);


        setEditClick(inputname, editname);
        setEditClick(inputemail, editemail);
        setEditClick(inputUser, cbEdit_user);

    }

    public void setOnClick(){
        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        btnterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TermsAndPoliciesActivity.class);
                startActivity(intent);
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        binding.dashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), DashBoardActivity.class);
                startActivity(i);
                binding.dashboardBtn.setVisibility(View.INVISIBLE);
                finish();
            }
        });
    }
}
