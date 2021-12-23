package com.nguyenvanhoa.book_app_reading.User.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.User.Adapter.MySliderAdapter;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.Home.Adapter.MyBookItemGroupAdapter;
import com.nguyenvanhoa.book_app_reading.User.Home.Interface.IFirebaseLoadListener;
import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemBookData;
import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemBookGroup;
import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemGroup;
import com.nguyenvanhoa.book_app_reading.User.Service.PicassoLoadingService;
import com.nguyenvanhoa.book_app_reading.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class HomeActivity extends AppCompatActivity implements IFirebaseLoadListener {
    IFirebaseLoadListener iFirebaseLoadListener;

    private BottomNavigationView navigationView;

    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iFirebaseLoadListener = this;

        //init binding
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Slider.init(new PicassoLoadingService());

        binding.myRecyclerView.setHasFixedSize(true);
        binding.myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadBanner();

        loadBookInsideCategory();

        Naviagation_bar();
    }

    private void loadBookInsideCategory() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ItemBookGroup> itemGroupList = new ArrayList<>();
                for (DataSnapshot ds:snapshot.getChildren()){
                    ItemBookGroup itemGroup = new ItemBookGroup();
                    String categoryId = ds.child("id").getValue(true).toString();
                    itemGroup.setId(categoryId);

                    ArrayList<ItemBookData> itemDataList = new ArrayList<>();

                    ItemBookData model = new ItemBookData(
                            "1639054861532",
                            "1639056470313",
                            "The Lincoln Highway",
                            "https://images-us.bookshop.org/ingram/9780735222359.jpg?height=500&v=v2-be30cbf0d08057581f8a4eaaa51103d7"
                    );
                    itemDataList.add(model);

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
                    ref.orderByChild("categoryId").equalTo(categoryId)
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    itemDataList.clear();
                                    for (DataSnapshot ds : snapshot.getChildren()){
                                        ItemBookData model = ds.getValue(ItemBookData.class);
                                        itemDataList.add(model);
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                    itemGroup.setListItem(itemDataList);
                    itemGroupList.add(itemGroup);
                }
                iFirebaseLoadListener.onFirebaseLoadsuccess1(itemGroupList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadBanner() {
        DatabaseReference refBanner = FirebaseDatabase.getInstance().getReference("Banners");
        refBanner.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> bannerList = new ArrayList<>();
                for (DataSnapshot ds:snapshot.getChildren()){
                    String image = ds.getValue(String.class);
                    bannerList.add(image);
                }
                binding.slider.setAdapter(new MySliderAdapter(HomeActivity.this, bannerList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                        finish();
                        break;
                    case R.id.nav_library:
                        intent = new Intent(getApplicationContext(), LibraryActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;
                    case R.id.nav_person:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        String userType = getIntent().getStringExtra("userType");
                        intent.putExtra("userType", userType);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;
                    case R.id.nav_search:
                        intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;
                }
                finish();
                return false;
            }
        });
    }

    @Override
    public void onFirebaseLoadsuccess(List<ItemGroup> itemGroupList) {

    }

    @Override
    public void onFirebaseLoadsuccess1(List<ItemBookGroup> itemGroupList) {
        MyBookItemGroupAdapter adapter = new MyBookItemGroupAdapter(this, itemGroupList);
        binding.myRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String messenger) {

    }
}