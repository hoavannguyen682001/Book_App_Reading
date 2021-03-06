package com.nguyenvanhoa.book_app_reading.User.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nguyenvanhoa.book_app_reading.User.Model.AllCategory;
import com.nguyenvanhoa.book_app_reading.User.Model.Book;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainRecyclerHolder> {

    private Context context;
    private List<AllCategory> allCategories;

    public void setData (List<AllCategory> list) {
        this.allCategories = list;
        notifyDataSetChanged();
    }

    public MainRecyclerAdapter(Context context, List<AllCategory> allCategories) {
        this.context = context;
        this.allCategories = allCategories;
    }

    @NonNull
    @Override
    public MainRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainRecyclerHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.MainRecyclerHolder holder, int position) {
        if (allCategories == null){
            return;
        }
        holder.categoryName.setText(allCategories.get(position).getCategorytitle());
//        SetItemRecycler(holder.itemRecycler, allCategories.get(position).getBookList());
    }

    @Override
    public int getItemCount() {
        return allCategories.size();
    }

    public class MainRecyclerHolder extends RecyclerView.ViewHolder{
        private TextView categoryName;
        private RecyclerView itemRecycler;

        public MainRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryTv);
            itemRecycler = itemView.findViewById(R.id.booksRv);
        }
    }
    private void SetItemRecycler(RecyclerView recyclerView, List<Book> bookList){
//        ItemCategoryAdapter itemCategoryAdapter = new ItemCategoryAdapter(context, bookList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
//        recyclerView.setAdapter(itemCategoryAdapter);
    }
}
