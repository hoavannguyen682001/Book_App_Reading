package com.nguyenvanhoa.book_app_reading.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nguyenvanhoa.book_app_reading.Activity.User.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.Model.Book;
import com.nguyenvanhoa.book_app_reading.Model.Book2;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.databinding.SmallItembookBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.ItemViewHolder> {

    Context context;
    List<Book> bookList;

    private SmallItembookBinding binding;
    public ItemCategoryAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SmallItembookBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ItemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Book book = bookList.get(position);
        String bookId = book.getId();
        String title = book.getTitle();
        String image = book.getImage();
        Picasso.get().load(image).into(holder.img);
        holder.title.setText(title);
//
//        holder.img.setImageResource(book.getImg());
//        holder.namebook.setText(book.getName());
//
//        holder.img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Book_Detail_Activity.nameClass = context.getClass().toString();
//                Intent intent = new Intent(context, Book_Detail_Activity.class);
//                intent.putExtra("bookName", bookList.get(position).getName());
//                intent.putExtra("bookAuthor", bookList.get(position).getAuthor());
//                intent.putExtra("bookDate", bookList.get(position).getDate());
//                intent.putExtra("bookCategory", bookList.get(position).getCategory());
//                intent.putExtra("bookImg", bookList.get(position).getImg());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private ImageView img;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = binding.txtNameBook;
            img = binding.imgBookItem;
        }
    }
}
