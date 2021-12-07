package com.nguyenvanhoa.book_app_reading.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nguyenvanhoa.book_app_reading.Activity.User.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.Model.Book2;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.List;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.ItemViewHolder> {

    Context context;
    List<Book2> bookList;
    public ItemCategoryAdapter(Context context, List<Book2> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.small_itembook, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Book2 book = bookList.get(position);

        holder.img.setImageResource(book.getImg());
        holder.namebook.setText(book.getName());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book_Detail_Activity.nameClass = context.getClass().toString();
                Intent intent = new Intent(context, Book_Detail_Activity.class);
                intent.putExtra("bookName", bookList.get(position).getName());
                intent.putExtra("bookAuthor", bookList.get(position).getAuthor());
                intent.putExtra("bookDate", bookList.get(position).getDate());
                intent.putExtra("bookCategory", bookList.get(position).getCategory());
                intent.putExtra("bookImg", bookList.get(position).getImg());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView namebook, authorbook, datebook, categorybook;
        private ImageView img;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            namebook = itemView.findViewById(R.id.txtName_book);
            img = itemView.findViewById(R.id.img_book_item);
        }
    }
}
