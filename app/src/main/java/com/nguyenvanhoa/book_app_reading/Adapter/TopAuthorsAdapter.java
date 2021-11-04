package com.nguyenvanhoa.book_app_reading.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nguyenvanhoa.book_app_reading.Model.TopAuthor;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.List;

public class TopAuthorsAdapter extends RecyclerView.Adapter<TopAuthorsAdapter.TopAuthorsViewHolder>{


    private List<TopAuthor> mListAuthors;

    public void setData(List<TopAuthor> list) {
        this.mListAuthors = list;
        notifyDataSetChanged ();
    }

    @NonNull
    @Override
    public TopAuthorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_authors, parent,false);
        return new TopAuthorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAuthorsViewHolder holder, int position) {
        TopAuthor topAuthor = mListAuthors.get(position);
        if (topAuthor == null){
            return;
        }
        holder.imgAuthor.setImageResource(topAuthor.getImgAuthor());
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
