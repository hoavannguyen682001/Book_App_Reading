package com.nguyenvanhoa.book_app_reading.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Adapter.MainRecyclerAdapter;
import com.nguyenvanhoa.book_app_reading.Adapter.SliderPagerAdapter;
import com.nguyenvanhoa.book_app_reading.Adapter.TopAuthorsAdapter;
import com.nguyenvanhoa.book_app_reading.Model.AllCategory;
import com.nguyenvanhoa.book_app_reading.Model.Book2;
import com.nguyenvanhoa.book_app_reading.Model.Slide_Show;
import com.nguyenvanhoa.book_app_reading.Model.TopAuthor;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

    private List<Slide_Show> listSlides;
    private ViewPager slide_pager;
    private SliderPagerAdapter adapter;
    private TabLayout indicator;
    private BottomNavigationView navigationView;

    private MainRecyclerAdapter mainRecyclerAdapter;
    private TopAuthorsAdapter topAuthorsAdapter;
    private RecyclerView rcv_mainRecycler;
    private RecyclerView rcv_topAuthors;
    private RecyclerView rcv_TopTrending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        InitView_Slide();
        InitView_RecyclerView();
        Naviagation_bar();
    }

    private void InitView_Slide() {
        slide_pager = findViewById(R.id.vpSlider);
        indicator = findViewById(R.id.indicator);

        adapter = new SliderPagerAdapter(this, getListSlides());
        slide_pager.setAdapter(adapter);


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),1000,3000);
        indicator.setupWithViewPager(slide_pager,true);
    }

    private List<Slide_Show> getListSlides() {
        listSlides = new ArrayList<>();
        listSlides.add(new Slide_Show(1, "The Dawn of Everything", R.drawable.banner1));
        listSlides.add(new Slide_Show(2, "The Stranger in the Lifeboat", R.drawable.banner2));
        return listSlides;
    }

    private void InitView_RecyclerView(){
        setMainRecycler();
    }


    public void setMainRecycler(){
        RecyclerView.LayoutManager layoutManager_Horizontal = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManager_Trending = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager_Category = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

//        rcv_TopTrending
        rcv_TopTrending = findViewById(R.id.rcv_TopTrending);
        rcv_TopTrending.setLayoutManager(layoutManager_Trending);
        rcv_TopTrending.setFocusable(false);
        rcv_TopTrending.setNestedScrollingEnabled(false);

        mainRecyclerAdapter = new MainRecyclerAdapter(this, GetListTrending());
        mainRecyclerAdapter.setData(GetListTrending());
        rcv_TopTrending.setAdapter(mainRecyclerAdapter);

//        rcv_topAuthors
        rcv_topAuthors = findViewById(R.id.rcv_topAuthors);
        rcv_topAuthors.setLayoutManager(layoutManager_Horizontal);
        rcv_topAuthors.setFocusable(false);
        rcv_topAuthors.setNestedScrollingEnabled(false);

        topAuthorsAdapter = new TopAuthorsAdapter(this, GetListAuthors());
        topAuthorsAdapter.setData(GetListAuthors());
        rcv_topAuthors.setAdapter(topAuthorsAdapter);

//        rcv_mainRecycler
        rcv_mainRecycler = findViewById(R.id.main_recycler);
        rcv_mainRecycler.setLayoutManager(layoutManager_Category);
        rcv_mainRecycler.setFocusable(false);
        rcv_mainRecycler.setNestedScrollingEnabled(false);

        mainRecyclerAdapter = new MainRecyclerAdapter(this, GetListCategory());
        mainRecyclerAdapter.setData(GetListCategory());
        rcv_mainRecycler.setAdapter(mainRecyclerAdapter);
    }

    private List<AllCategory> GetListTrending() {
        List<Book2> bookTrending = new ArrayList<>();
        bookTrending.add(new Book2("The Dawn of Everything", "by John Grisham", "August 19, 2014", "Horror",R.drawable.the_dawn));
        bookTrending.add(new Book2("Dune","by John Grisham", "August 19, 2014", "Horror",R.drawable.dune ));
        bookTrending.add(new Book2("The Story of Schitt's Creek","by John Grisham", "August 19, 2014", "Horror",R.drawable.the_story_of_schit_creek));
        bookTrending.add(new Book2("The 1619 Project","by John Grisham", "August 19, 2014", "Horror",R.drawable.the_1619_project));
        bookTrending.add(new Book2( "Poems","by John Grisham", "August 19, 2014", "Horror",R.drawable.poems));

        List<AllCategory> trending_list = new ArrayList<>();
        trending_list.add(new AllCategory(1, "Trending Books ", bookTrending));
        return trending_list;
    }

    private List<TopAuthor> GetListAuthors() {
        List<TopAuthor> list = new ArrayList<>();
        list.add(new TopAuthor(R.drawable.author, "John Grisham"));
        list.add(new TopAuthor(R.drawable.author1, "Scotland Kenneth Grahame"));
        list.add(new TopAuthor(R.drawable.author2, "William Golding"));
        list.add(new TopAuthor(R.drawable.author3, "J.K. Rowling"));
        list.add(new TopAuthor(R.drawable.author4, " Ernest Hemingway"));
        return list;
    }

 private List<AllCategory> GetListCategory(){
        List<Book2> mListBookEducation = new ArrayList<>();
        mListBookEducation.add(new Book2( "School Leaders","by John Grisham", "August 19, 2014", "Horror",R.drawable.school_leaders));
        mListBookEducation.add(new Book2( "Shaping School Culture","by John Grisham", "August 19, 2014", "Horror",R.drawable.shaping_school_culture));
        mListBookEducation.add(new Book2( "Our Country Friends","by John Grisham", "August 19, 2014", "Horror",R.drawable.our_country_friends));
        mListBookEducation.add(new Book2( "Pedagogy","by John Grisham", "August 19, 2014", "Horror",R.drawable.pedagogy));

        List<Book2> mListBook2 = new ArrayList<>();
        mListBook2.add(new Book2("The Stranger in the Lifeboat","by John Grisham", "August 19, 2014", "Horror",R.drawable.the_stranger));
        mListBook2.add(new Book2("Big Fish & Begonia","by John Grisham", "August 19, 2014", "Horror",R.drawable.bigfish_begonia));
        mListBook2.add(new Book2("Tales from Earthsea","by John Grisham", "August 19, 2014", "Horror",R.drawable.tales_from_earthsea));
        mListBook2.add(new Book2("when marnie was there","by John Grisham", "August 19, 2014", "Horror",R.drawable.when_marnie_was_there));

        List<Book2> mListBook3 = new ArrayList<>();
        mListBook3.add(new Book2("Braiding Sweetgrass","by John Grisham", "August 19, 2014", "Horror",R.drawable.braiding_sweetgrass));
        mListBook3.add(new Book2( "How to Write a Mystery","by John Grisham", "August 19, 2014", "Horror",R.drawable.how_to_write_a_mystery));
        mListBook3.add(new Book2("One Writer's Beginnings","by John Grisham", "August 19, 2014", "Horror", R.drawable.one_writers_beginnings));
        mListBook3.add(new Book2( "Olympus, Texas","by John Grisham", "August 19, 2014", "Horror",R.drawable.olympus_texas));

        List<Book2> mListBook4 = new ArrayList<>();
        mListBook4.add(new Book2( "Circe","by John Grisham", "August 19, 2014", "Horror",R.drawable.circe));
        mListBook4.add(new Book2("Ariadne","by John Grisham", "August 19, 2014", "Horror",R.drawable.ariadne));
        mListBook4.add(new Book2( "Slide Version 2","by John Grisham", "August 19, 2014", "Horror",R.drawable.shaping_school_culture));
        mListBook4.add(new Book2( "Moana","by John Grisham", "August 19, 2014", "Horror",R.drawable.moana));

        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Education ", mListBookEducation));
        allCategoryList.add(new AllCategory(2, "Detective and Mystery", mListBook2));
        allCategoryList.add(new AllCategory(3, "Fantasy", mListBook3));
        allCategoryList.add(new AllCategory(4, "Historical Fiction", mListBook4));

        return allCategoryList;
    }

//    //get categories from db firebase
//    private List<AllCategory> GetListCategory(){
//        List<AllCategory> allCategoryList = new ArrayList<>();
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Catogeries");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                allCategoryList.clear();
//
//                for (DataSnapshot ds: snapshot.getChildren()) {
//                    AllCategory allCategory = ds.getValue(AllCategory.class);
//
//                    allCategoryList.add(allCategory);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return allCategoryList;
//    }
    class SliderTimer extends TimerTask {
        @Override
        public void run() {

            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (slide_pager.getCurrentItem()<listSlides.size()-1) {
                        slide_pager.setCurrentItem(slide_pager.getCurrentItem()+1);
                    }
                    else
                        slide_pager.setCurrentItem(0);
                }
            });
        }
    }
    public void Naviagation_bar(){
        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Intent intent;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
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