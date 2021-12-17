package com.nguyenvanhoa.book_app_reading.User.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.book_app_reading.User.Model.Book;
import com.nguyenvanhoa.book_app_reading.databinding.ItemRecyclerBinding;

import java.util.List;

public class TopTrendingAdapter extends RecyclerView.Adapter<TopTrendingAdapter.myViewHolder> {
    private Context context;
    private List<Book> bookList;
    private ItemRecyclerBinding binding;
    public TopTrendingAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRecyclerBinding.inflate(LayoutInflater.from(context), parent, false);
        return new myViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.category.setText("Top Trending");
        holder.booksRv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        getDataItemRecycler(holder.booksRv, bookList);
    }

    public void getDataItemRecycler(RecyclerView recyclerView, List<Book> bookList){
        ItemCategoryAdapter itemCategoryAdapter = new ItemCategoryAdapter(context, bookList);
        recyclerView.setAdapter(itemCategoryAdapter);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView category;
        RecyclerView booksRv;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            category = binding.categoryTv;
            booksRv = binding.booksRv;
        }
    }
}
