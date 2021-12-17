package com.nguyenvanhoa.book_app_reading.User.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.nguyenvanhoa.book_app_reading.Admin.Activity.PdfDetailActivity;
import com.nguyenvanhoa.book_app_reading.Admin.Filter.FilterPdf;
import com.nguyenvanhoa.book_app_reading.Admin.Models.MyApplication;
import com.nguyenvanhoa.book_app_reading.User.Model.Book;
import com.nguyenvanhoa.book_app_reading.databinding.ItemBookBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterBook extends RecyclerView.Adapter<AdapterBook.HolderBook>{
    private Context context;
    public ArrayList<Book> bookArrayList, filterList;
    private ItemBookBinding binding;
    private FilterPdf filter;
    FirebaseAuth firebaseAuth;

    public AdapterBook(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
        this.filterList = bookArrayList;
    }

    @NonNull
    @Override
    public HolderBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBookBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderBook(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBook holder, int position) {
        Book model = bookArrayList.get(position);
        String pdfId = model.getId();
        String categoryId = model.getCategoryId();
        String title = model.getTitle();
        String author = model.getAuthor();
        String pdfUrl = model.getUrl();

        String image = model.getImage();
        long timestamp = model.getTimestamp();

        String formatDate = MyApplication.formatTimestamp(timestamp);
        holder.titleTv.setText(title);
        holder.authorTv.setText(author);
        holder.dateTv.setText(formatDate);
        Picasso.get().load(image).into(holder.img);
        MyApplication.loadCategory(holder.categoryTv, ""+categoryId);
//        MyApplication.loadPdfFromUrlPage(""+pdfUrl, ""+ title, holder.pdfView, context, holder.progressBar);
//        MyApplication.loadPdfSize(""+pdfUrl, ""+title, holder.sizeTv, context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PdfDetailActivity.class);
                i.putExtra("bookId", pdfId);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }


    class HolderBook extends RecyclerView.ViewHolder{
//        PDFView pdfView;
//        ProgressBar progressBar;
        TextView titleTv, categoryTv, dateTv, authorTv;

        ImageView img;
        public HolderBook(@NonNull View itemView) {
            super(itemView);
            titleTv = binding.titleTv;
            categoryTv = binding.categoryTv;
            authorTv = binding.authorTv;
            dateTv = binding.dateTv;
            img = binding.imgBook;
        }
    }
}
