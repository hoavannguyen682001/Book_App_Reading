package com.nguyenvanhoa.book_app_reading.Activity.Admin.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.barteksc.pdfviewer.PDFView;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Activity.PdfDetailActivity;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Activity.PdfEditActivity;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Filter.FilterPdf;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Models.MyApplication;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Models.PdfModel;
import com.nguyenvanhoa.book_app_reading.databinding.RowPdfBinding;

import java.util.ArrayList;

public class AdapterPdf extends RecyclerView.Adapter<AdapterPdf.HolderPdf> implements Filterable {
    private Context context;
    public ArrayList<PdfModel> pdfArrayList, filterList;
    private RowPdfBinding binding;
    private FilterPdf filter;

    public AdapterPdf(Context context, ArrayList<PdfModel> pdfArrayList) {
        this.context = context;
        this.pdfArrayList = pdfArrayList;
        this.filterList = pdfArrayList;
    }

    @NonNull
    @Override
    public HolderPdf onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowPdfBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderPdf(binding.getRoot());
    }


    @Override
    public void onBindViewHolder(@NonNull HolderPdf holder, int position) {
        PdfModel model = pdfArrayList.get(position);
        String pdfId = model.getId();
        String categoryId = model.getCategoryId();
        String title = model.getTitle();
        String description = model.getDescription();
        String pdfUrl = model.getUrl();
        long timestamp = model.getTimestamp();

        String formatDate = MyApplication.formatTimestamp(timestamp);
        holder.titleTv.setText(title);
        holder.descriptionTv.setText(description);
        holder.dateTv.setText(formatDate);


        MyApplication.loadCategory(holder.categoryTv, ""+categoryId);
        MyApplication.loadPdfFromUrlPage(""+pdfUrl, ""+ title, holder.pdfView, context, holder.progressBar);
        MyApplication.loadPdfSize(""+pdfUrl, ""+title, holder.sizeTv, context);
        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOptionDialog(model, holder);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PdfDetailActivity.class);
                i.putExtra("bookId", pdfId);
                context.startActivity(i);
            }
        });
    }

    private void moreOptionDialog(PdfModel model, HolderPdf holder) {
        String bookId = model.getId();
        String bookurl = model.getUrl();
        String bookTitle = model.getTitle();
        String[] options = {"Edit", "Delete"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose Options")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent i = new Intent(context, PdfEditActivity.class);
                                i.putExtra("bookId", bookId);
                                context.startActivity(i);
                                break;
                            case 1:
                                MyApplication.deleteBook(context, ""+bookId, ""+bookurl, ""+bookTitle);
                                break;
                        }
                    }
                })
                .show();
    }

    @Override
    public int getItemCount() {
        return pdfArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new FilterPdf(filterList, this);
        }
        return filter;
    }

    class HolderPdf extends RecyclerView.ViewHolder{
        PDFView pdfView;
        ProgressBar progressBar;
        TextView titleTv, descriptionTv, categoryTv, sizeTv, dateTv;
        ImageButton moreBtn;

        public HolderPdf(@NonNull View itemView) {
            super(itemView);
            pdfView = binding.pdfView;
            progressBar = binding.progressBar;
            titleTv = binding.titleTv;
            descriptionTv = binding.descriptionTv;
            categoryTv = binding.categoryTv;
            sizeTv = binding.sizeTv;
            dateTv = binding.dateTv;
            moreBtn = binding.moreBtn;
        }
    }
}
