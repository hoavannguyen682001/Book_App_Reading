package com.nguyenvanhoa.book_app_reading.User.RecyclerView_Book;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.book_app_reading.User.Model.Book2;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.databinding.ItemBookBinding;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder>{
    Context context;
    private List<Book2> Books;
    private ItemBookBinding binding;
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
    public void onBindViewHolder(@NonNull BookHolder holder, @SuppressLint("RecyclerView") int position) {
//        Book2 book = Books.get(position);
//        if(book == null){
//            return;
//        }
//        holder.imgBook.setImageResource(book.getImg());
//        holder.name.setText(book.getName());
//        holder.author.setText(book.getAuthor());
//        holder.date.setText(book.getDate());
//        holder.category.setText(book.getCategory());

//        holder.imgview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Book_Detail_Activity.nameClass = context.getClass().toString();
//                Intent intent = new Intent(context, Book_Detail_Activity.class);
//                intent.putExtra("bookName", Books.get(position).getName());
//                intent.putExtra("bookAuthor", Books.get(position).getAuthor());
//                intent.putExtra("bookDate", Books.get(position).getDate());
//                intent.putExtra("bookCategory", Books.get(position).getCategory());
//                intent.putExtra("bookImg", Books.get(position).getImg());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(Books != null){
            return Books.size();
        }
        return 0;
    }

    public class BookHolder extends RecyclerView.ViewHolder{

        ImageView imgBook, imgview;
        TextView name, author, date, category;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
//            imgview = itemView.findViewById(R.id.imageView);
            imgBook = itemView.findViewById(R.id.img_book_item);
            name = itemView.findViewById(R.id.txtName_book);
            author = itemView.findViewById(R.id.txtAuthor_book);
            date = itemView.findViewById(R.id.txtDate_book);
            category = itemView.findViewById(R.id.txtCategory_book);
        }
    }
}
