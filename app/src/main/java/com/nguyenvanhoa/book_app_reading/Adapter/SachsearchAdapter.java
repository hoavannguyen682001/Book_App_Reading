package com.nguyenvanhoa.book_app_reading.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.book_app_reading.Activity.User.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.Model.Book2;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.List;

public class SachsearchAdapter extends RecyclerView.Adapter<SachsearchAdapter.SachViewHoder> {
    private List<Book2> listSach;
    private Context mContext;

    public SachsearchAdapter(Context context ,List<Book2> listSach) {
        this.mContext = context;
        this.listSach = listSach;
    }
    @NonNull
    @Override
    public SachViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent, false);
        return new SachViewHoder(view);
    }
    @Override

    public void onBindViewHolder(@NonNull SachViewHoder holder, @SuppressLint("RecyclerView") int position) {
        Book2 book = listSach.get(position);
        if (book == null){
            return;
        }
        holder.imgBook.setImageResource(book.getImg());
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
        holder.date.setText(book.getDate());
        holder.category.setText(book.getCategory());
        holder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book_Detail_Activity.nameClass = mContext.getClass().toString();
                Intent intent = new Intent(mContext, Book_Detail_Activity.class);
                intent.putExtra("bookName", listSach.get(position).getName());
                intent.putExtra("bookImg", listSach.get(position).getImg());
                mContext.startActivity(intent);
            }
        });
    }
    private void onClickGoToDetail(Book2 sach){

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
        ImageView imgBook, imgview;
        TextView name, author, date, category;
        public SachViewHoder(@NonNull View itemView) {
            super(itemView);
//            imgview = itemView.findViewById(R.id.imageView);
//            imgBook = itemView.findViewById(R.id.img_book_item);
//            name = itemView.findViewById(R.id.txtName_book);
//            author = itemView.findViewById(R.id.txtAuthor_book);
//            date = itemView.findViewById(R.id.txtDate_book);
//            category = itemView.findViewById(R.id.txtCategory_book);
        }
    }
    public void filterList(List<Book2> list){//timkiem
        this.listSach = list;
        notifyDataSetChanged();
    }
}
