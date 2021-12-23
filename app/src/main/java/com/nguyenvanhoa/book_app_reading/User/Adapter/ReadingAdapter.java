package com.nguyenvanhoa.book_app_reading.User.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Admin.Activity.PdfDetailActivity;
import com.nguyenvanhoa.book_app_reading.Admin.Models.MyApplication;
import com.nguyenvanhoa.book_app_reading.User.Model.Book;
import com.nguyenvanhoa.book_app_reading.databinding.ItemBookBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.HolderBookReading>{

    private Context context;
    private ArrayList<Book> bookArrayList;

    private ItemBookBinding binding;

    public ReadingAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public HolderBookReading onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBookBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderBookReading(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBookReading holder, int position) {
        Book model = bookArrayList.get(position);

        loadBookDetail(model, holder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PdfDetailActivity.class);
                intent.putExtra("bookId", model.getId());
                context.startActivity(intent);
            }
        });
    }

    private void loadBookDetail(Book model, HolderBookReading holder) {
        String bookId = model.getId();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.child(bookId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String bookTitle = snapshot.child("title").getValue().toString();
                        String bookAuthor = snapshot.child("author").getValue().toString();

                        String timestamp = snapshot.child("timestamp").getValue().toString();
                        String categoryId = snapshot.child("categoryId").getValue().toString();
                        String url = snapshot.child("url").getValue().toString();
                        String uid = snapshot.child("uid").getValue().toString();

                        //image
                        String image = snapshot.child("Image").getValue().toString();
//
                        model.setTitle(bookTitle);
                        model.setAuthor(bookAuthor);
                        model.setTimestamp(Long.parseLong(timestamp));
                        model.setCategoryId(categoryId);
                        model.setUid(uid);
                        model.setUrl(url);
                        model.setImage(image);
                        String date = MyApplication.formatTimestamp(Long.parseLong(timestamp));
//
                        holder.titleTv.setText(bookTitle);
                        holder.authorTv.setText(bookAuthor);
                        holder.dateTv.setText(date);
                        Picasso.get().load(model.getImage()).into(holder.imageView);
                        MyApplication.loadCategory(holder.categoryTv, ""+categoryId);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    class HolderBookReading extends RecyclerView.ViewHolder{

        TextView titleTv, authorTv, dateTv, categoryTv ;
        ImageView imageView;

        public HolderBookReading(@NonNull View itemView) {
            super(itemView);
            titleTv = binding.titleTv;
            authorTv = binding.authorTv;
            dateTv = binding.dateTv;
            categoryTv = binding.categoryTv;
            imageView = binding.imgBook;
        }
    }
}
