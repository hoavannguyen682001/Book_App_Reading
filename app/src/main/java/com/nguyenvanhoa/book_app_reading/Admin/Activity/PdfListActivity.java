package com.nguyenvanhoa.book_app_reading.Admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Admin.Adapter.AdapterPdf;
import com.nguyenvanhoa.book_app_reading.Admin.Models.PdfModel;
import com.nguyenvanhoa.book_app_reading.databinding.ActivityPdfListBinding;

import java.util.ArrayList;

public class PdfListActivity extends AppCompatActivity {
    private ActivityPdfListBinding binding;
    private ArrayList<PdfModel> pdfArrayList;
    private AdapterPdf adapterPdf;
    private String categoryId, categoryTitle;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfListBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        setContentView(R.layout.activity_pdf_list);
        setContentView(binding.getRoot());
        Intent i = getIntent();
        categoryId = i.getStringExtra("categoryId");
        categoryTitle = i.getStringExtra("categoryTitle");
        loadPdfList();

        binding.subTitleTv.setText(categoryTitle);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    adapterPdf.getFilter().filter(s);
                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void loadPdfList() {
        pdfArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.orderByChild("categoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i= 0;
                        pdfArrayList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()){
                            PdfModel model = ds.getValue(PdfModel.class);
                            pdfArrayList.add(model);
                            i++;
                        }
//                        Toast.makeText(getApplication(), ""+i, Toast.LENGTH_SHORT).show();
                        adapterPdf = new AdapterPdf(PdfListActivity.this, pdfArrayList);
                        binding.booksRv.setAdapter(adapterPdf);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplication(), "line 59! loadPdfList Fail "+ error.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                });
    }
}