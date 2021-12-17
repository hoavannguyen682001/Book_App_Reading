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
import com.nguyenvanhoa.book_app_reading.databinding.ItemBookVoteBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.HolderBookFavorite>{

    private Context context;
    private ArrayList<Book> bookArrayList;

    //binding item row
    private ItemBookVoteBinding binding;

    public FavoriteAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public HolderBookFavorite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBookVoteBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderBookFavorite(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBookFavorite holder, int position) {
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

        holder.removeFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.removeFromFavorite(context, model.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public void loadBookDetail(Book model, HolderBookFavorite holder){
        String bookId = model.getId();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.child(bookId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String booktitle = snapshot.child("title").getValue().toString();
//                        String bookAuthor = snapshot.child("author").getValue().toString();
                        String bookAuthor = "null";

                        String timestamp = snapshot.child("timestamp").getValue().toString();
                        String categoryId = snapshot.child("categoryId").getValue().toString();
                        String url = snapshot.child("url").getValue().toString();
                        String uid = snapshot.child("uid").getValue().toString();

                        String image = snapshot.child("Image").getValue().toString();
                        //
                        model.setFavorite(true);
                        model.setTitle(booktitle);
                        model.setAuthor(bookAuthor);
                        model.setTimestamp(Long.parseLong(timestamp));
                        model.setCategoryId(categoryId);
                        model.setUid(uid);
                        model.setUrl(url);
                        model.setImage(image);
                        String date = MyApplication.formatTimestamp(Long.parseLong(timestamp));
//
                        holder.titleTv.setText(booktitle);
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
    //viewHolder class
    class HolderBookFavorite extends RecyclerView.ViewHolder{

        TextView titleTv, authorTv, dateTv, categoryTv ;
        ImageView removeFavBtn, imageView;
        public HolderBookFavorite(@NonNull View itemView) {
            super(itemView);
            titleTv = binding.titleTv;
            authorTv = binding.authorTv;
            dateTv = binding.dateTv;
            categoryTv = binding.categoryTv;
            removeFavBtn = binding.removeFavBtn;
            imageView = binding.imgBook;
        }
    }
}
