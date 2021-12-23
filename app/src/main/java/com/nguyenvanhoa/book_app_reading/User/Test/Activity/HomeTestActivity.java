package com.nguyenvanhoa.book_app_reading.User.Test.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Admin.Models.Category;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.Activity.BookshelfActivity;
import com.nguyenvanhoa.book_app_reading.User.Activity.HomeActivity;
import com.nguyenvanhoa.book_app_reading.User.Activity.LibraryActivity;
import com.nguyenvanhoa.book_app_reading.User.Activity.ProfileActivity;
import com.nguyenvanhoa.book_app_reading.User.Activity.SearchActivity;
import com.nguyenvanhoa.book_app_reading.User.Adapter.MySliderAdapter;
import com.nguyenvanhoa.book_app_reading.User.Model.Book;
import com.nguyenvanhoa.book_app_reading.User.Service.PicassoLoadingService;
import com.nguyenvanhoa.book_app_reading.User.ViewHolder.CategoryTwoViewHolder;
import com.nguyenvanhoa.book_app_reading.User.ViewHolder.CategoryViewHolder;
import com.nguyenvanhoa.book_app_reading.databinding.ActivityHomeBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class HomeTestActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;
    FirebaseRecyclerAdapter<Book, CategoryTwoViewHolder> adapter2;
    RecyclerView.LayoutManager manager;

    FirebaseDatabase database;
    DatabaseReference refCategory;

    private ActivityHomeBinding binding;

    private ProgressDialog progressDialog;

    private BottomNavigationView navigationView;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Slider.init(new PicassoLoadingService());

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        loadBanner();
        loadDataRecyclerViewInSideRecyclerView();
        Naviagation_bar();
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
                binding.slider.setAdapter(new MySliderAdapter(HomeTestActivity.this, bannerList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadDataRecyclerViewInSideRecyclerView (){

        database = FirebaseDatabase.getInstance();
        refCategory = database.getReference("Categories");
        manager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(manager);

        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(refCategory,Category.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull Category model) {
                holder.categoryName.setText(model.getCategory());

                FirebaseRecyclerOptions<Book> options2 = new FirebaseRecyclerOptions.Builder<Book>()
                        .setQuery(refCategory.child(model.getId()).child("Books"),Book.class)
                        .build();
                adapter2 = new FirebaseRecyclerAdapter<Book, CategoryTwoViewHolder>(options2) {
                    @Override
                    protected void onBindViewHolder(@NonNull CategoryTwoViewHolder holder, int position, @NonNull Book model) {
                        holder.dataName.setText(model.getTitle());
                        Picasso.get().load(model.getImage()).into(holder.imgBook);
                        progressDialog.dismiss();
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(HomeTestActivity.this, ""+model.getImage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public CategoryTwoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v2 = LayoutInflater.from(getBaseContext())
                                .inflate(R.layout.layout_item,parent,false);
                        return new CategoryTwoViewHolder(v2);
                    }
                };
                adapter2.startListening();
                adapter2.notifyDataSetChanged();
                holder.category_recyclerView.setAdapter(adapter2);
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v1 = LayoutInflater.from(getBaseContext())
                        .inflate(R.layout.layout_group,parent,false);
                return new CategoryViewHolder(v1);
            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
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
}