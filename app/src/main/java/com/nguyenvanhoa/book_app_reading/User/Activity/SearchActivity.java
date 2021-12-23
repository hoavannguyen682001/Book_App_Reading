package com.nguyenvanhoa.book_app_reading.User.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;

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
import com.nguyenvanhoa.book_app_reading.User.Adapter.AdapterBook;
import com.nguyenvanhoa.book_app_reading.User.Adapter.CategoryAdapter;
import com.nguyenvanhoa.book_app_reading.User.Adapter.SachsearchAdapter;
import com.nguyenvanhoa.book_app_reading.User.Model.Book;
import com.nguyenvanhoa.book_app_reading.User.Model.Book2;
import com.nguyenvanhoa.book_app_reading.User.Model.Category;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.Test.Activity.HomeTestActivity;

import java.util.ArrayList;
import java.util.List;
public class SearchActivity extends AppCompatActivity {
    private ArrayList<Book> bookArrayList;
    private Spinner spnCategory;
    private CategoryAdapter categoryAdapter;
    private RecyclerView rcvSach;
    private AdapterBook adapterBook;

    private RecyclerView.LayoutManager mLayoutManager;
    private EditText editText;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);

        Navigation_bar();
        rcvSach = findViewById(R.id.rcv_book);
        rcvSach.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        rcvSach.setLayoutManager(mLayoutManager);

        spnCategory = findViewById(R.id.spn_category);
        categoryAdapter = new CategoryAdapter(this, R.layout.itemsearch_selected, getListCategory());
        spnCategory.setAdapter(categoryAdapter);

        getAllBooks();

        editText= findViewById(R.id.edittext);//timkiem
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    adapterBook.getFilter().filter(charSequence);
                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private List<Category> getListCategory(){
        List<Category> list = new ArrayList<>();
        list.add(new Category("Book"));
        list.add(new Category("Author"));
        list.add(new Category("Category"));
        return  list;
    }
    private void getAllBooks(){
        //init list
        bookArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference( "Books");
        ref.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //clear list before starting adding data into it
                bookArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    //get data
                    Book model = ds.getValue(Book.class);
                    //add to list
                    bookArrayList.add(model);
                }
                //setup adapter
                adapterBook = new AdapterBook(SearchActivity.this, bookArrayList);
                //set adapter to recyclerview
                rcvSach.setAdapter(adapterBook);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                        intent = new Intent(getApplicationContext(), HomeTestActivity.class);
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
