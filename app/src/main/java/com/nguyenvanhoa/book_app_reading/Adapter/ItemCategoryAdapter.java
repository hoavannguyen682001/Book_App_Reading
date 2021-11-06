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


import com.nguyenvanhoa.book_app_reading.Activity.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.Model.Book;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.List;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.ItemViewHolder> {

    Context context;
    List<Book> bookList;

    public ItemCategoryAdapter(Context context, List<Book> bookList) {
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
        Book book = bookList.get(position);
        holder.img.setImageResource(book.getImg());
        holder.namebook.setText(book.getName());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book_Detail_Activity.nameClass = context.getClass().toString();
                Intent intent = new Intent(context, Book_Detail_Activity.class);
                intent.putExtra("bookName", bookList.get(position).getName());
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

        private TextView namebook, author, date, category;
        private ImageView img;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            namebook = itemView.findViewById(R.id.txtName_book);
            img = itemView.findViewById(R.id.img_book_item);
            author = itemView.findViewById(R.id.txtAuthor_book);
            date = itemView.findViewById(R.id.txtDate_book);
            category = itemView.findViewById(R.id.txtCategory_book);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    bookItemClick.onClickItem(bookList.get(getAdapterPosition()),
//                            img,
//                            namebook,
//                            author,
//                            date,
//                            category);
//                }
//            });
        }
    }
}
