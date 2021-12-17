package com.nguyenvanhoa.book_app_reading.User.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.Home.Interface.IItemclickListener;
import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.myHolderAdapter>{

    private Context context;
    private List<ItemData> itemDataList;

    public MyItemAdapter(Context context, List<ItemData> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public myHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new myHolderAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolderAdapter holder, int position) {
        holder.txt_item_title.setText(itemDataList.get(position).getName());
        Picasso.get().load(itemDataList.get(position).getImage()).into(holder.img_item);

        holder.setiItemclickListener(new IItemclickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(context.getApplicationContext(), ""+itemDataList.get(position).getImage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }

    class myHolderAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_item_title;
        ImageView img_item;

        IItemclickListener iItemclickListener;

        public void setiItemclickListener(IItemclickListener iItemclickListener) {
            this.iItemclickListener = iItemclickListener;
        }

        public myHolderAdapter(@NonNull View itemView) {
            super(itemView);
            txt_item_title = itemView.findViewById(R.id.tvTitle);
            img_item = itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemclickListener.onItemClickListener(v, getAdapterPosition());
        }
    }
}
