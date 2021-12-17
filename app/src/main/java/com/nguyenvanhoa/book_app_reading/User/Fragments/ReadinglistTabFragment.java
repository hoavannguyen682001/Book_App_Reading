package com.nguyenvanhoa.book_app_reading.User.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.User.Adapter.ReadingAdapter;
import com.nguyenvanhoa.book_app_reading.User.Model.Book;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.ArrayList;

public class ReadinglistTabFragment extends Fragment {
    private RecyclerView rvbooks;
    private ArrayList<Book> bookArrayList;
    private ReadingAdapter adapterBook;

    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_readinglist, container, false);
        rvbooks = root.findViewById(R.id.rcvBook);
        firebaseAuth = FirebaseAuth.getInstance();
        rvbooks.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadBookReading();
        return root;
    }

    private void loadBookReading() {
        bookArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid()).child("Reading")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        bookArrayList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()){
                            String bookId = ""+ds.child("bookId").getValue();
                            Book model = ds.getValue(Book.class);
                            model.setId(bookId);
                            bookArrayList.add(model);
                        }
                        adapterBook = new ReadingAdapter(getContext(), bookArrayList);
                        rvbooks.setAdapter(adapterBook);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
