package com.nguyenvanhoa.book_app_reading.RecyclerView_Book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Activity.PdfDetailActivity;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Models.MyApplication;
import com.nguyenvanhoa.book_app_reading.Activity.User.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.Model.Book;
import com.nguyenvanhoa.book_app_reading.Model.Book2;
import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.databinding.ItemBookVoteBinding;

import java.util.ArrayList;
import java.util.List;

public class BookAdapterVote extends RecyclerView.Adapter<BookAdapterVote.BookHolder>{
    Context context;
//    private List<Book2> Books;
    private ArrayList<Book> bookArrayList;
    private ItemBookVoteBinding binding;

    public BookAdapterVote(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBookVoteBinding.inflate(LayoutInflater.from(context), parent, false);
        return new BookHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {

        Book model = bookArrayList.get(position);
        loadBookDetail(model, holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PdfDetailActivity.class);
                intent.putExtra("bookId", model.getId());
                context.startActivity(intent);
            }
        });
        
        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Remove Book", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadBookDetail(Book model, BookHolder holder) {
        String bookId = model.getId();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Books");
        ref.child(bookId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String title = snapshot.child("title").getValue().toString();
                        String author = snapshot.child("author").getValue().toString();
                        String date = snapshot.child("timestamp").getValue().toString();
                        String uid = snapshot.child("uid").getValue().toString();
                        String bookUrl = snapshot.child("url").toString();
                        String categoryId = snapshot.child("categoryId").getValue().toString();

                        model.setTitle(title);
                        model.setAuthor(author);
                        model.setFavorite(true);
                        model.setTimestamp(Long.parseLong(date));
                        model.setCategoryId(categoryId);
                        model.setUid(uid);
                        model.setUrl(bookUrl);

                        String dateFomat = MyApplication.formatTimestamp(Long.parseLong(date));
                        holder.title.setText(title);
                        holder.author.setText(author);
                        holder.date.setText(dateFomat);
//                         loadCategory(categoryId);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder{

        TextView title, author, date, category;
        ImageButton favBtn;
        PDFView pdfView;
//        ProgressBar progressBar;
        public BookHolder(@NonNull View itemView) {
            super(itemView);
            title = binding.titleTv;
            author = binding.authorTv;
            date = binding.dateTv;
            category = binding.categoryTv;
            favBtn = binding.removeFavBtn;
//            pdfView = binding.pdfView;
//            progressBar = binding.progressBar;
        }
    }
//
//    public BookAdapterVote(Context context, List<Book2> books) {
//        this.context = context;
//        Books = books;
//    }
//
//    @NonNull
//    @Override
//    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_book_vote, parent, false);
//        return new BookHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BookHolder holder, @SuppressLint("RecyclerView") int position) {
//        Book2 book = Books.get(position);
//        if(book == null){
//            return;
//        }
//        holder.imgBook.setImageResource(book.getImg());
//        holder.name.setText(book.getName());
//        holder.author.setText(book.getAuthor());
//        holder.date.setText(book.getDate());
//        holder.category.setText(book.getCategory());
//        holder.imgview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "successfully!", Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(context, Book_Detail_Activity.class);
//                intent.putExtra("bookName", Books.get(position).getName());
//                intent.putExtra("bookAuthor", Books.get(position).getAuthor());
//                intent.putExtra("bookDate", Books.get(position).getDate());
//                intent.putExtra("bookCategory", Books.get(position).getCategory());
//                intent.putExtra("bookImg", Books.get(position).getImg());
//                context.startActivity(intent);
//            }
//        });
//        holder.checkbox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(holder.checkbox.isChecked() == false){
//                    Books.remove(holder.getAdapterPosition());
//                    notifyItemRemoved(holder.getAdapterPosition());
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        if(Books != null){
//            return Books.size();
//        }
//        return 0;
//    }
//
//    public class BookHolder extends RecyclerView.ViewHolder{
//        CheckBox checkbox;
//        ImageView imgBook, imgview;
//        TextView name, author, date, category;
//        public BookHolder(@NonNull View itemView) {
//            super(itemView);
//            imgview = itemView.findViewById(R.id.imageView);
//            imgBook = itemView.findViewById(R.id.img_book_item);
//            name = itemView.findViewById(R.id.txtName_book);
//            author = itemView.findViewById(R.id.txtAuthor_book);
//            date = itemView.findViewById(R.id.txtDate_book);
//            category = itemView.findViewById(R.id.txtCategory_book);
//            checkbox = itemView.findViewById(R.id.cbfavorite);
//        }
//    }
}
