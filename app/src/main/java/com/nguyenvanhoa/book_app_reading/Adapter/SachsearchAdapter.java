package com.nguyenvanhoa.book_app_reading.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.book_app_reading.Activity.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.Model.Sach_search;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.List;

public class SachsearchAdapter extends RecyclerView.Adapter<SachsearchAdapter.SachViewHoder> {
    private List<Sach_search> listSach;
    private Context mContext;

    public SachsearchAdapter(Context context ,List<Sach_search> listSach) {
        this.mContext = context;
        this.listSach = listSach;
    }
    @NonNull
    @Override
    public SachViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent, false);
        return new SachViewHoder(view);
    }
    @Override

    public void onBindViewHolder(@NonNull SachViewHoder holder, @SuppressLint("RecyclerView") int position) {
      Sach_search sach = listSach.get(position);
        if (sach == null){
            return;
        }
        holder.hinhanh.setImageResource(sach.getHinhanh());
        holder.tensach.setText(sach.getTensach());
        holder.tacgia.setText(sach.getTacgia());
        holder.ngay.setText(sach.getNgay());
        holder.theloai.setText(sach.getTheloai());
        holder.layoutitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book_Detail_Activity.nameClass = mContext.getClass().toString();
                Intent intent = new Intent(mContext, Book_Detail_Activity.class);
                intent.putExtra("bookName", listSach.get(position).getTensach());
                intent.putExtra("bookImg", listSach.get(position).getHinhanh());
                mContext.startActivity(intent);
            }
        });
    }
    private void onClickGoToDetail(Sach_search sach){

//        Bundle bundle = new Bundle();
//        bundle.putSerializable("Object_sach", sach);
//        intent.putExtras(bundle);
    }
    @Override
    public int getItemCount() {
        if(listSach != null){
            return listSach.size();
        }
        return 0;
    }

    public class SachViewHoder extends RecyclerView.ViewHolder{
        private LinearLayout layoutitem;
        private ImageView hinhanh;
        private TextView tensach;
        private TextView tacgia;
        private TextView ngay;
        private  TextView theloai;
        public SachViewHoder(@NonNull View itemView) {
            super(itemView);
            layoutitem= itemView.findViewById(R.id.layoutitem);
            hinhanh =itemView.findViewById(R.id.picture) ;
            tensach = itemView.findViewById(R.id.name);
            tacgia = itemView.findViewById(R.id.author);
            ngay = itemView.findViewById(R.id.date);
            theloai = itemView.findViewById(R.id.Catogary);
        }
    }
    public void filterList(List<Sach_search> list){//timkiem
        this.listSach = list;
        notifyDataSetChanged();
    }
}
