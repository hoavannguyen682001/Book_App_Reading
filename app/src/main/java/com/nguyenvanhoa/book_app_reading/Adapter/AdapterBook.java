package com.nguyenvanhoa.book_app_reading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Activity.PdfDetailActivity;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Filter.FilterPdf;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Models.MyApplication;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Models.PdfModel;
import com.nguyenvanhoa.book_app_reading.Model.Book;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.databinding.ItemBookBinding;

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
        firebaseAuth = FirebaseAuth.getInstance();
        Book model = bookArrayList.get(position);
        String pdfId = model.getId();
        String categoryId = model.getCategoryId();
        String title = model.getTitle();
        String author = model.getAuthor();
        String pdfUrl = model.getUrl();
        long timestamp = model.getTimestamp();

        String formatDate = MyApplication.formatTimestamp(timestamp);
        holder.titleTv.setText(title);
        holder.authorTv.setText(author);
        holder.dateTv.setText(formatDate);

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
//        if (firebaseAuth.getCurrentUser() != null){
//            checkIsFavorite(pdfId);
//        }
//        holder.addFavBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (firebaseAuth.getCurrentUser() == null){
//                    Toast.makeText(context, "not login", Toast.LENGTH_SHORT).show();
//                }else{
//                    if (isInMyFavorite){
//                        MyApplication.removeFromFavori(context, pdfId);
//                    }else{
//                        MyApplication.addToFavorite(context, pdfId);
//                    }
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }


    class HolderBook extends RecyclerView.ViewHolder{
//        PDFView pdfView;
//        ProgressBar progressBar;
        TextView titleTv, categoryTv, dateTv, authorTv;
        public HolderBook(@NonNull View itemView) {
            super(itemView);
            titleTv = binding.titleTv;
            categoryTv = binding.categoryTv;
            authorTv = binding.authorTv;
            dateTv = binding.dateTv;
        }
    }
}
