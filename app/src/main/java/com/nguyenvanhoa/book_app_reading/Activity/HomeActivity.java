package com.nguyenvanhoa.book_app_reading.Activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nguyenvanhoa.book_app_reading.Adapter.MainRecyclerAdapter;
import com.nguyenvanhoa.book_app_reading.Adapter.SliderPagerAdapter;
import com.nguyenvanhoa.book_app_reading.Model.AllCategory;
import com.nguyenvanhoa.book_app_reading.Model.Book;
import com.nguyenvanhoa.book_app_reading.Model.Slide_Show;
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

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        InitView_Slide();
        InitView_RecyclerView();
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
        setMainRecycler(SetListRecycler());
    }

    private List<AllCategory> SetListRecycler(){
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

    public void setMainRecycler(List<AllCategory> allCategoryList){
        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
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

}