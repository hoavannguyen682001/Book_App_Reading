package com.nguyenvanhoa.book_app_reading.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nguyenvanhoa.book_app_reading.R;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    private ImageButton btnlogout;
    private CheckBox editname, editphone, editemail, editgender;
    private AutoCompleteTextView gender;
    private EditText inputname, inputphone, inputemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnlogout = findViewById(R.id.btnlogout);
        Navigation_bar();

        inputname = findViewById(R.id.input_name);
        editname = findViewById(R.id.cbEdit_name);

        inputphone = findViewById(R.id.input_phone);
        editphone = findViewById(R.id.cbEdit_phone);

        inputemail = findViewById(R.id.input_email);
        editemail = findViewById(R.id.cbEdit_email);

        gender = findViewById(R.id.Gender_Profile);
        editgender = findViewById(R.id.cbEdit_Gender);

        setEditClick(inputname, editname);
        setEditClick(inputphone, editphone);
        setEditClick(inputemail, editemail);

        editgender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editgender.isChecked()){
                    gender.setEnabled(true);
                    InitGender(gender);
                }else{
                    gender.setEnabled(false);
                    gender.setText(gender.getText());
                }
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
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
    private void InitGender(AutoCompleteTextView gender) {
//        ArrayList<String> listGender = new ArrayList<>();
//        listGender.add("Male");
//        listGender.add("Female");
//        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout., listGender);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnGender.setAdapter(adapter);
        String[] arrGender = new String[]{"Male", "Female", "Other"};
        ArrayAdapter<CharSequence> GenderAdapter = new ArrayAdapter<CharSequence>(this, R.layout.custom_spinner, arrGender);
        gender.setAdapter(GenderAdapter);
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
}
