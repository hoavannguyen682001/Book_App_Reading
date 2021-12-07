package com.nguyenvanhoa.book_app_reading.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nguyenvanhoa.book_app_reading.Activity.User.SearchActivity;
import com.nguyenvanhoa.book_app_reading.Model.TopAuthor;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.List;

public class TopAuthorsAdapter extends RecyclerView.Adapter<TopAuthorsAdapter.TopAuthorsViewHolder> {

    private Context context;
    private List<TopAuthor> mListAuthors;

    public void setData(List<TopAuthor> list) {
        this.mListAuthors = list;
        notifyDataSetChanged ();
    }

    public TopAuthorsAdapter(Context context, List<TopAuthor> mListAuthors) {
        this.context = context;
        this.mListAuthors = mListAuthors;
    }

    @NonNull
    @Override
    public TopAuthorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_authors, parent,false);
        return new TopAuthorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAuthorsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TopAuthor topAuthor = mListAuthors.get(position);
        if (topAuthor == null){
            return;
        }
        holder.imgAuthor.setImageResource(topAuthor.getImgAuthor());
        holder.imgAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SearchActivity.class);
                i.putExtra("bookName", mListAuthors.get(position).getImgAuthor());
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
            imgAuthor = itemView.findViewById(R.id.avt_author);
        }
    }
}
