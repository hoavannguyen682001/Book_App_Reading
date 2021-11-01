package com.nguyenvanhoa.book_app_reading.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nguyenvanhoa.book_app_reading.Model.Book;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder>{
    private List<Book> mBookList;

    public BookAdapter(List<Book> mBookList) {
        this.mBookList = mBookList;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_itembook, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Book book = mBookList.get(position);
        if(book==null){
            return;
        }
        holder.imgBook.setImageResource(book.getImg());
        holder.name.setText(book.getName());
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    class BookHolder extends RecyclerView.ViewHolder{

        ImageView imgBook;
        TextView name;
        public BookHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.img_book_item);
            name = itemView.findViewById(R.id.txtName_book);
        }
    }
}
