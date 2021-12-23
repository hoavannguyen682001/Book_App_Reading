package com.nguyenvanhoa.book_app_reading.User.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.book_app_reading.R;


public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView categoryName;
    public RecyclerView category_recyclerView;
    public RecyclerView.LayoutManager manager;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        manager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryName = itemView.findViewById(R.id.itemTitle);
        category_recyclerView = itemView.findViewById(R.id.recycler_view_item_list);
        category_recyclerView.setLayoutManager(manager);
    }
}
