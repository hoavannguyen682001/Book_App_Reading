package com.nguyenvanhoa.book_app_reading.User.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.book_app_reading.Admin.Activity.PdfDetailActivity;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.Model.Book;
import com.nguyenvanhoa.book_app_reading.databinding.ItemBookBinding;
import com.nguyenvanhoa.book_app_reading.databinding.SmallItembookBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopTrendingAdapter extends RecyclerView.Adapter<TopTrendingAdapter.myViewHolder> {
    private Context context;
    private SmallItembookBinding binding;
    private ArrayList<Book> bookArrayList;

    public TopTrendingAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookArrayList = bookList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SmallItembookBinding.inflate(LayoutInflater.from(context), parent, false);
        return new myViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Book model = bookArrayList.get(position);
        String id = model.getId();
        String title = model.getTitle();
        holder.titleBook.setText(model.getTitle());
        String image = model.getImage();
        Picasso.get().load(image).into(holder.imgBook);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PdfDetailActivity.class);
                i.putExtra("bookId", model.getId());
                i.putExtra("categoryId", model.getCategoryId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        public TextView titleBook;
        public ImageView imgBook;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            titleBook = binding.txtNameBook;
            imgBook = binding.imgBookItem;
        }
    }
}
