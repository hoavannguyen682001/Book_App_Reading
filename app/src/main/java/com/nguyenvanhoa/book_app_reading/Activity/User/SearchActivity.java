package com.nguyenvanhoa.book_app_reading.Activity.User;

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
import com.nguyenvanhoa.book_app_reading.Adapter.CategoryAdapter;
import com.nguyenvanhoa.book_app_reading.Adapter.SachsearchAdapter;
import com.nguyenvanhoa.book_app_reading.Model.Book2;
import com.nguyenvanhoa.book_app_reading.Model.Category;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.ArrayList;
import java.util.List;
public class SearchActivity extends AppCompatActivity {
    ArrayList<Book2> books;
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

        rcvSach.setLayoutManager(new LinearLayoutManager(getApplication()));
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
                buildRecycleView();
                filter(editable.toString());
            }
        });
    }
    private void createlist() {
        books = new ArrayList<>();
        books.add(new Book2("GrindelWall Row", "John Grisham", "August 19, 2014", "Horror", R.drawable.the_story_of_schit_creek));
        books.add(new Book2("Themartian", "Grisham", "August 19, 2014", "Family", R.drawable.braiding_sweetgrass));
        books.add(new Book2("Spider Cover", "Joisham", "August 19, 2014", "Action", R.drawable.the_stranger));
        books.add(new Book2("Sycamore Row", "Hisham", "August 19, 2014", "Education", R.drawable.circe));
        books.add(new Book2("Werewolves", "John Grisham", "August 19, 2014", "Horror", R.drawable.school_leaders));
        books.add(new Book2("Moana", "Joam", "August 19, 2014", "Horror", R.drawable.shaping_school_culture));
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
        sachadapter = new SachsearchAdapter(this,books);
        rcvSach.setLayoutManager(mLayoutManager);
        rcvSach.setAdapter(sachadapter);
    }

    private void filter(String text){  //timkiem
        ArrayList<Book2> ab = new ArrayList<>();
        for (Book2 sach: books){
            if(sach.getName().toLowerCase().contains(text.toLowerCase())){
                ab.add(sach);
            }
            else if(sach.getCategory().toLowerCase().contains(text.toLowerCase())){
                ab.add(sach);
            }
            else if(sach.getCategory().toLowerCase().contains(text.toLowerCase())){
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
