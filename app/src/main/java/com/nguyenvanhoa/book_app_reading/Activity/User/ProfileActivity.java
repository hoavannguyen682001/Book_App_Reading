package com.nguyenvanhoa.book_app_reading.Activity.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Activity.DashBoardActivity;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.databinding.ActivityProfileBinding;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    private ActivityProfileBinding binding;
    public static String userType;
    private FirebaseAuth firebaseAuth;
    String userId, fullName, email;
    private Uri imageUri = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());
        initView();

        enableDashboard();

        setOnClick();

        Navigation_bar();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait.....");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        loadUserInfo();
    }

    private void loadUserInfo() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        userId = firebaseUser.getUid();
        //check in db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        String email = ""+snapshot.child("email").getValue();
                        String fullname = ""+snapshot.child("fullName").getValue();
                        String profileImage = ""+snapshot.child("profileImage").getValue();

                        binding.nameTv.setText(fullname);
                        binding.nameEt.setText(fullname);
                        binding.emailEt.setText(email);

                        Glide.with(ProfileActivity.this)
                                .load(profileImage)
                                .placeholder(R.drawable.ic_person)
                                .into(binding.profileIv);
                        progressDialog.dismiss();
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
//        ref.child(firebaseAuth.getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Toast.makeText(getApplication(), ""+firebaseAuth.getUid(), Toast.LENGTH_SHORT).show();
//                        String email = ""+snapshot.child("email").getValue();
//                        Toast.makeText(getApplication(), ""+email, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
    }

    public void enableDashboard(){
        if(userType.equals("admin")){
            binding.dashboardBtn.setVisibility(View.VISIBLE);
        }
    }

    public void initView(){
        setEditClick(binding.nameEt, binding.cbEditName);
        setEditClick(binding.emailEt, binding.cbEditEmail);
    }

    private void setEditClick(EditText input, CheckBox checkBox){
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    input.setEnabled(true);
                }else{
                    // save info

                    fullName = binding.nameEt.getText().toString().trim();
                    email = binding.emailEt.getText().toString().trim();
                    if(TextUtils.isEmpty(fullName)){
                        Toast.makeText( getApplication(), "Enter your name...", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(email)){
                        Toast.makeText( getApplication(), "Enter your email...", Toast.LENGTH_SHORT).show();
                    }else {
                        if(imageUri == null){
                            updateProfile("");
                        }else{
                            uploadImage();
                        }
                    }
                }
            }
        });
    }

    private void uploadImage() {
        progressDialog.setMessage("Updating profile image...");
        progressDialog.show();

        //image path and name, use uid to replace previous
        String filePathAndName = "ProfileImage/"+firebaseAuth.getUid();

        //storage reference
        StorageReference reference = FirebaseStorage.getInstance ().getReference (filePathAndName);
        reference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful());
                        String upLoadedImageUrl = ""+uriTask.getResult();
                        updateProfile(upLoadedImageUrl);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplication(), "Failed To Upload Image due to "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void updateProfile(String imageUrl) {
        progressDialog.setMessage("Updating your profile....");
        progressDialog.show();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", ""+email);
        hashMap.put("fullName", ""+fullName);
        if (imageUri != null){
            hashMap.put("profileImage", ""+imageUrl);
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(userId)
                .updateChildren(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplication(), "Update Success", Toast.LENGTH_SHORT).show();
                        binding.nameEt.setEnabled(false);
                        binding.emailEt.setEnabled(false);
                        binding.nameEt.setText(fullName);
                        binding.emailEt.setText(email);
                        loadUserInfo();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplication(), "Update Fail", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }

    public void setOnClick(){
        binding.btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        binding.termBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TermsAndPoliciesActivity.class);
                startActivity(intent);
            }
        });
        binding.btnlogout.setOnClickListener(new View.OnClickListener() {
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

        binding.profileIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageAttackMenu();
            }
        });
    }

    private void showImageAttackMenu() {
        PopupMenu popupMenu = new PopupMenu(this, binding.profileIv);
        popupMenu.getMenu().add(Menu.NONE, 0, 0, "Camera");
        popupMenu.getMenu().add(Menu.NONE, 1, 1, "Gallery");
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int which = item.getItemId();
                if(which == 0){
                    //open camera
                    pickImageCamera();

                }else if(which == 1){
                    //gallery
                    pickImageGalerry();
                }
                return false;
            }
        });
    }

    private void pickImageCamera() {
        //intent to pick image from camera
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Pick");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Sample Image Description");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        cameraActivityResultLauncher.launch(i);
    }

    private void pickImageGalerry() {
        Intent intent = new Intent(Intent. ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        binding.profileIv.setImageURI(imageUri);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        imageUri = data.getData();
                        binding.profileIv.setImageURI(imageUri);
                        //upload profile image

                    }
                }
            }
    );
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
