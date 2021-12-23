package com.nguyenvanhoa.book_app_reading.User.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.book_app_reading.R;


public class CategoryTwoViewHolder extends RecyclerView.ViewHolder {

    public TextView dataName;
    public ImageView imgBook;
    public CategoryTwoViewHolder(@NonNull View itemView) {
        super(itemView);
        dataName = itemView.findViewById(R.id.tvTitle);
        imgBook = itemView.findViewById(R.id.itemImage);
    }
}
