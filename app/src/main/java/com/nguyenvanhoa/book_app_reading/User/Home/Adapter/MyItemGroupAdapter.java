package com.nguyenvanhoa.book_app_reading.User.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemData;
import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemGroup;

import java.util.List;

public class MyItemGroupAdapter extends RecyclerView.Adapter<MyItemGroupAdapter.myHolderView>{

    private Context context;
    private List<ItemGroup> dataList;
    public MyItemGroupAdapter(Context context, List<ItemGroup> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public myHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_group, parent, false);
        return new myHolderView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolderView holder, int position) {

        holder.titleTv.setText(dataList.get(position).getHeaderTitle());

        List<ItemData> itemData = dataList.get(position).getListItem();

        MyItemAdapter itemListAdapter = new MyItemAdapter(context, itemData);
        holder.recycler_view_list.setHasFixedSize(true);
        holder.recycler_view_list.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        holder.recycler_view_list.setAdapter(itemListAdapter);
        holder.recycler_view_list.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class myHolderView extends RecyclerView.ViewHolder {

        TextView titleTv;
        RecyclerView recycler_view_list;
        public myHolderView(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.itemTitle);
            recycler_view_list = itemView.findViewById(R.id.recycler_view_item_list);
        }
    }
}
