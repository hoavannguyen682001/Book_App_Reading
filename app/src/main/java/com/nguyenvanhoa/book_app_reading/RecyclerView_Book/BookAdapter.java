package com.nguyenvanhoa.book_app_reading.RecyclerView_Book;


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
import com.nguyenvanhoa.book_app_reading.Model.Book2;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder>{
    Context context;
    private List<Book2> Books;


    public BookAdapter(Context context, List<Book2> books) {
        this.context = context;
        Books = books;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_book, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Book2 book = Books.get(position);
        if(book == null){
            return;
        }
        holder.imgBook.setImageResource(book.getImg());
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
        holder.date.setText(book.getDate());
        holder.category.setText(book.getCategory());
        holder.imgBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "successfully!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, Book_Detail_Activity.class);
                intent.putExtra("bookName", Books.get(position).getName());
                intent.putExtra("bookImg", Books.get(position).getImg());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(Books != null){
            return Books.size();
        }
        return 0;
    }

    public class BookHolder extends RecyclerView.ViewHolder{

        ImageView imgBook;
        TextView name, author, date, category;
        public BookHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.img_book_item);
            name = itemView.findViewById(R.id.txtName_book);
            author = itemView.findViewById(R.id.txtAuthor_book);
            date = itemView.findViewById(R.id.txtDate_book);
            category = itemView.findViewById(R.id.txtCategory_book);
        }
    }
}
