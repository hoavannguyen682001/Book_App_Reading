package com.nguyenvanhoa.book_app_reading.User.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Admin.Activity.PdfListActivity;
import com.nguyenvanhoa.book_app_reading.Admin.Adapter.AdapterPdf;
import com.nguyenvanhoa.book_app_reading.Admin.Models.PdfModel;
import com.nguyenvanhoa.book_app_reading.User.Adapter.AdapterBook;
import com.nguyenvanhoa.book_app_reading.User.Adapter.CategoryAdapter;
import com.nguyenvanhoa.book_app_reading.User.Adapter.SachsearchAdapter;
import com.nguyenvanhoa.book_app_reading.User.Model.Book;
import com.nguyenvanhoa.book_app_reading.User.Model.Book2;
import com.nguyenvanhoa.book_app_reading.User.Model.Category;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.List;
public class SearchActivity extends AppCompatActivity {
    private ArrayList<Book> bookArrayList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView rcvSach;
    private AdapterBook adapterBook;

    private RecyclerView.LayoutManager mLayoutManager;
    private EditText editText;
    private BottomNavigationView navigationView;
    private String authorName, category, categoryId;

    private ActivitySearchBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Navigation_bar();
        rcvSach = findViewById(R.id.rcv_book);
        rcvSach.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        rcvSach.setLayoutManager(mLayoutManager);

        Intent i = getIntent();
        authorName = i.getStringExtra("authorName");
        category = i.getStringExtra("category");
        categoryId = i.getStringExtra("categoryId");
        getAllBooks();

        editText= findViewById(R.id.searchEt);//timkiem
    }

    private void getAllBooks(){
        //init list
        bookArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference( "Books");
        if (authorName != null){
            binding.searchEt.setText(authorName);
            ref.orderByChild("author").equalTo(authorName)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            getBook(snapshot);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
        }else if (category != null){
            binding.searchEt.setText(category);
            ref.orderByChild("categoryId").equalTo(categoryId)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            getBook(snapshot);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
        }else{
            ref.addValueEventListener (new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    getBook(snapshot);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

    public void getBook(DataSnapshot snapshot){
        bookArrayList.clear();
        for (DataSnapshot ds : snapshot.getChildren()) {
            Book model = ds.getValue(Book.class);
            bookArrayList.add(model);
        }
        adapterBook = new AdapterBook(SearchActivity.this, bookArrayList);
        rcvSach.setAdapter(adapterBook);
    }
    public void Navigation_bar(){
        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.nav_search);
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
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.nav_search:

                        break;
                }
                return false;
            }
        });
    }
}
