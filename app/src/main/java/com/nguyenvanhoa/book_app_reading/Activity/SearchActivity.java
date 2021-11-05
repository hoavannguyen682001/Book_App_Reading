package com.nguyenvanhoa.book_app_reading.Activity;

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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nguyenvanhoa.book_app_reading.Adapter.CategoryAdapter;
import com.nguyenvanhoa.book_app_reading.Adapter.SachsearchAdapter;
import com.nguyenvanhoa.book_app_reading.Model.Book;
import com.nguyenvanhoa.book_app_reading.Model.Category;
import com.nguyenvanhoa.book_app_reading.Model.Sach_search;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.ArrayList;
import java.util.List;
public class SearchActivity extends AppCompatActivity {
    ArrayList<Sach_search> list;
    private Spinner spnCategory;
    private CategoryAdapter categoryAdapter;
    private RecyclerView rcvSach;
    private SachsearchAdapter sachadapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText editText;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);

        Navigation_bar();

        createlist();
        buildRecycleView();
        spnCategory = findViewById(R.id.spn_category);
        categoryAdapter = new CategoryAdapter(this, R.layout.itemsearch_selected, getListCategory());
        spnCategory.setAdapter(categoryAdapter);

        rcvSach.setLayoutManager(new LinearLayoutManager(this));
        editText= findViewById(R.id.edittext);//timkiem
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());
            }
        });
    }
    private void createlist() {
        list = new ArrayList<>();
        list.add(new Sach_search(R.drawable.title_book,"Sycamore Trees", "By John Grisham", "October 19,2017","Love story"));
        list.add(new Sach_search(R.drawable.truyen,"The Wind in the Willows", "Scotland Kenneth Grahame", "October 19,2017","Love story"));
        list.add(new Sach_search(R.drawable.truyen,"Lord of the Flies", "William Golding", "October 19,2017","Love story"));
        list.add(new Sach_search(R.drawable.title_book,"The Old Man and Sea", " Ernest Hemingway", "October 19,2017","Love story"));
        list.add(new Sach_search(R.drawable.truyen,"The Giver", "Lois Lowry", "October 19,2017","Love story"));
        list.add(new Sach_search(R.drawable.title_book,"Harry Potter", "J.K. Rowling", "October 19,2017","Love story"));
    }
    private List<Category> getListCategory(){
        List<Category> list = new ArrayList<>();
        list.add(new Category("Book"));
        list.add(new Category("Author"));
        list.add(new Category("Category"));
        return  list;
    }
    private void buildRecycleView(){
        rcvSach = findViewById(R.id.rcv_book);
        rcvSach.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        sachadapter = new SachsearchAdapter(this,list);
        rcvSach.setLayoutManager(mLayoutManager);
        rcvSach.setAdapter(sachadapter);
    }
    private void filter(String text){  //timkiem
        ArrayList<Sach_search> ab = new ArrayList<>();
        for (Sach_search sach: list){
            if(sach.getTensach().toLowerCase().contains(text.toLowerCase())){
                ab.add(sach);
            }
            else if(sach.getTacgia().toLowerCase().contains(text.toLowerCase())){
                ab.add(sach);
            }
            else if(sach.getTheloai().toLowerCase().contains(text.toLowerCase())){
                ab.add(sach);
            }
        }
        sachadapter.filterList(ab);
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
