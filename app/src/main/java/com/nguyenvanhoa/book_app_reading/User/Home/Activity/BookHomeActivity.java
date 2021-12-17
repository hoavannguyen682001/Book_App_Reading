package com.nguyenvanhoa.book_app_reading.User.Home.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.Home.Adapter.MyBookItemGroupAdapter;
import com.nguyenvanhoa.book_app_reading.User.Home.Adapter.MyItemGroupAdapter;
import com.nguyenvanhoa.book_app_reading.User.Home.Interface.IFirebaseLoadListener;
import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemBookGroup;
import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemData;
import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemGroup;

import java.util.ArrayList;
import java.util.List;

public class BookHomeActivity extends AppCompatActivity implements IFirebaseLoadListener {

    IFirebaseLoadListener iFirebaseLoadListener;
    RecyclerView my_recycler_view;
    DatabaseReference myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_book_activity);
        myData = FirebaseDatabase.getInstance().getReference ("MyData");

        iFirebaseLoadListener = this;

        my_recycler_view = findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        getFirebaseData();
    }

    private void getFirebaseData() {
        myData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ItemGroup> itemGroupList = new ArrayList<>();
                for (DataSnapshot ds:snapshot.getChildren()){
                    ItemGroup itemGroup = new ItemGroup();
                    String title = ds.child("headerTitle").getValue(true).toString();
                    itemGroup.setHeaderTitle(title);
//                    GenericTypeIndicator<ArrayList<ItemData>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<ItemData>>() {};
                    GenericTypeIndicator<ArrayList<ItemData>> t = new GenericTypeIndicator<ArrayList<ItemData>>() {};

                    ArrayList<ItemData> itemDataList = new ArrayList<>();
                    ItemData model = new ItemData(title,
                            "2",
                            "https://www.pngarts.com/files/3/Pizza-PNG-Image.png"
                            );
//                    ItemData model = new ItemData();
                    itemDataList.add(model);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("book");
                    ref.orderByChild("categoryId").equalTo(title)
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot ds : snapshot.getChildren()){
                                        ItemData model = ds.getValue(ItemData.class);
                                        itemDataList.add(model);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

//                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("book");
//                    ref1.orderByChild( "categoryId").equalTo("Drink")
//                            .addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                    for (DataSnapshot ds : snapshot.getChildren()){
//                                        itemGroup.setListItem(ds.child("data").getValue(t));
//                                        snapshot.getValue(t);
//                                    }
//
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//
//                                }
//                            });
                    itemGroup.setListItem(itemDataList);
//                    Toast.makeText(getApplication(), ""+getListItem(title).size(), Toast.LENGTH_SHORT).show();
                    itemGroupList.add(itemGroup);
                }
                iFirebaseLoadListener.onFirebaseLoadsuccess(itemGroupList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onFirebaseLoadsuccess(List<ItemGroup> itemGroupList) {
        MyItemGroupAdapter adapter = new MyItemGroupAdapter(this, itemGroupList);
        my_recycler_view.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadsuccess1(List<ItemBookGroup> itemGroupList) {
        MyBookItemGroupAdapter adapter = new MyBookItemGroupAdapter(this, itemGroupList);
        my_recycler_view.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String messenger) {

    }
}