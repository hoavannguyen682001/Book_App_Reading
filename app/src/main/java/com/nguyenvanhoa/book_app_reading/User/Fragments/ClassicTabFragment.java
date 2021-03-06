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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.User.Adapter.AdapterBook;
import com.nguyenvanhoa.book_app_reading.User.Model.Book;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.ArrayList;

public class ClassicTabFragment extends Fragment {
    private RecyclerView rvbooks;
    private ArrayList<Book> bookArrayList;
    private AdapterBook adapterBook;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_classic, container, false);
        rvbooks = root.findViewById(R.id.rcvBook);
        rvbooks.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadBookShelf();
        return root;
    }

    public void loadBookShelf() {
        bookArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.orderByChild("downloadsCount").limitToFirst(10)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        bookArrayList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()){
                            Book model = ds.getValue(Book.class);
                            bookArrayList.add(model);
                        }
                        adapterBook = new AdapterBook(getContext(), bookArrayList);
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
