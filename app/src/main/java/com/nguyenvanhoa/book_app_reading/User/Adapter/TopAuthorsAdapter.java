package com.nguyenvanhoa.book_app_reading.User.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nguyenvanhoa.book_app_reading.User.Activity.SearchActivity;
import com.nguyenvanhoa.book_app_reading.User.Model.Author;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.databinding.ItemTopAuthorsBinding;
import com.nguyenvanhoa.book_app_reading.databinding.SmallItembookBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopAuthorsAdapter extends RecyclerView.Adapter<TopAuthorsAdapter.TopAuthorsViewHolder> {

    private Context context;
    private ArrayList<Author> mListAuthors;
    private ItemTopAuthorsBinding binding;

    public TopAuthorsAdapter(Context context, ArrayList<Author> mListAuthors) {
        this.context = context;
        this.mListAuthors = mListAuthors;
    }

    @NonNull
    @Override
    public TopAuthorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTopAuthorsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new TopAuthorsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull TopAuthorsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Author model = mListAuthors.get(position);
        if (model == null){
            return;
        }
        String image = model.getAuthorImage();
        Picasso.get().load(image).into(holder.imgAuthor);

        holder.imgAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SearchActivity.class);
                i.putExtra("authorName", model.getAuthorName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListAuthors != null){
            return mListAuthors.size();
        }
        return 0;
    }

    public class TopAuthorsViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAuthor;
        public TopAuthorsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAuthor = binding.avtAuthor;
        }
    }
}
