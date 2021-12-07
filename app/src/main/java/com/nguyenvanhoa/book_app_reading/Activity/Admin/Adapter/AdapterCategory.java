package com.nguyenvanhoa.book_app_reading.Activity.Admin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.book_app_reading.Activity.Admin.Activity.PdfListActivity;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Filter.FilterCategory;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Models.Category;
import com.nguyenvanhoa.book_app_reading.Activity.User.ProfileActivity;
import com.nguyenvanhoa.book_app_reading.databinding.RowCategoryBinding;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.HolderView> implements Filterable {
    private Context context;
    public ArrayList<Category> categoryArrayList, filterList;
    private FilterCategory filter;

    private RowCategoryBinding binding;
    public AdapterCategory(Context context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
        this.filterList = categoryArrayList;
    }
    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowCategoryBinding.inflate(LayoutInflater.from(context), parent,  false);
        return new HolderView(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {
        Category model = categoryArrayList.get(position);
        String id = model.getId();
        String category = model.getCategory();
        String uid = model.getUid();
        long timestamp = model.getTimestamp();
        holder.categoryTv.setText(category);

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PdfListActivity.class);
                i.putExtra("categoryId", id);
                i.putExtra("categoryTitle", category);

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new FilterCategory(filterList, this);
        }
        return filter;
    }

    public class HolderView extends RecyclerView.ViewHolder{
        TextView categoryTv;
        ImageButton deleteBtn;

        public HolderView(@NonNull View itemView) {
            super(itemView);
            categoryTv = binding.categoryTv;
            deleteBtn = binding.deleteBtn;
        }
    }
}
