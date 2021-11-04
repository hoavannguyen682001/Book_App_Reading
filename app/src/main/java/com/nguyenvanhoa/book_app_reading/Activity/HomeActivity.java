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
import com.nguyenvanhoa.book_app_reading.Adapter.MainRecyclerAdapter;
import com.nguyenvanhoa.book_app_reading.Adapter.SliderPagerAdapter;
import com.nguyenvanhoa.book_app_reading.Adapter.TopAuthorsAdapter;
import com.nguyenvanhoa.book_app_reading.Model.AllCategory;
import com.nguyenvanhoa.book_app_reading.Model.Book;
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

    MainRecyclerAdapter mainRecyclerAdapter;
    TopAuthorsAdapter topAuthorsAdapter;
    RecyclerView rcv_mainRecycler;
    RecyclerView rcv_topAuthors;
    List<AllCategory> allCategoryList;

    
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
        listSlides.add(new Slide_Show(1, "slide1", R.drawable.slide1));
        listSlides.add(new Slide_Show(1, "spidercover", R.drawable.slide2));
        listSlides.add(new Slide_Show(1, "moana", R.drawable.spidercover));
        return listSlides;
    }


    private void InitView_RecyclerView(){
        setMainRecycler();
    }


    public void setMainRecycler(){
        RecyclerView.LayoutManager layoutManager_Horizontal = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManager_Vertical = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

//        rcv_topAuthors
        rcv_topAuthors = findViewById(R.id.rcv_topAuthors);
        rcv_topAuthors.setLayoutManager(layoutManager_Horizontal);
        rcv_topAuthors.setFocusable(false);
        rcv_topAuthors.setNestedScrollingEnabled(false);

        topAuthorsAdapter = new TopAuthorsAdapter();
        topAuthorsAdapter.setData(GetListAuthors());
        rcv_topAuthors.setAdapter(topAuthorsAdapter);

//        rcv_mainRecycler
        rcv_mainRecycler = findViewById(R.id.main_recycler);
        rcv_mainRecycler.setLayoutManager(layoutManager_Vertical);
        rcv_mainRecycler.setFocusable(false);
        rcv_mainRecycler.setNestedScrollingEnabled(false);

        mainRecyclerAdapter = new MainRecyclerAdapter(this, GetListCategory());
        mainRecyclerAdapter.setData(GetListCategory());
        rcv_mainRecycler.setAdapter(mainRecyclerAdapter);
    }

    private List<TopAuthor> GetListAuthors() {
        List<TopAuthor> list = new ArrayList<>();
        list.add(new TopAuthor(R.drawable.author));
        list.add(new TopAuthor(R.drawable.author1));
        list.add(new TopAuthor(R.drawable.author2));
        list.add(new TopAuthor(R.drawable.author3));
        list.add(new TopAuthor(R.drawable.author4));
        list.add(new TopAuthor(R.drawable.author));
        list.add(new TopAuthor(R.drawable.author1));
        list.add(new TopAuthor(R.drawable.author2));
        list.add(new TopAuthor(R.drawable.author3));
        list.add(new TopAuthor(R.drawable.author4));
        list.add(new TopAuthor(R.drawable.author2));
        list.add(new TopAuthor(R.drawable.author4));
        return list;
    }

    private List<AllCategory> GetListCategory(){
        List<Book> mListBook1 = new ArrayList<>();
        mListBook1.add(new Book(R.drawable.spidercover, "Spider Cover"));
        mListBook1.add(new Book(R.drawable.slide1, "Slide Version 1"));
        mListBook1.add(new Book(R.drawable.slide2, "Slide Version 2"));
        mListBook1.add(new Book(R.drawable.moana, "Moana"));

        List<Book> mListBook2 = new ArrayList<>();
        mListBook2.add(new Book(R.drawable.themartian, "Spider Cover"));
        mListBook2.add(new Book(R.drawable.bigfish_begonia, "Big Fish & Begonia"));
        mListBook2.add(new Book(R.drawable.tales_from_earthsea, "Tales from Earthsea"));
        mListBook2.add(new Book(R.drawable.when_marnie_was_there, "When Marnie Was There"));

        List<Book> mListBook3 = new ArrayList<>();
        mListBook3.add(new Book(R.drawable.spidercover, "Spider Cover"));
        mListBook3.add(new Book(R.drawable.slide1, "Slide Version 1"));
        mListBook3.add(new Book(R.drawable.slide2, "Slide Version 2"));
        mListBook3.add(new Book(R.drawable.moana, "Moana"));

        List<Book> mListBook4 = new ArrayList<>();
        mListBook4.add(new Book(R.drawable.spidercover, "Spider Cover"));
        mListBook4.add(new Book(R.drawable.slide1, "Slide Version 1"));
        mListBook4.add(new Book(R.drawable.slide2, "Slide Version 2"));
        mListBook4.add(new Book(R.drawable.moana, "Moana"));

        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Action ", mListBook1));
        allCategoryList.add(new AllCategory(2, "Family", mListBook2));
        allCategoryList.add(new AllCategory(3, "Dramas", mListBook3));
        allCategoryList.add(new AllCategory(4, "Action & Adventure", mListBook4));

        return allCategoryList;
    }

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