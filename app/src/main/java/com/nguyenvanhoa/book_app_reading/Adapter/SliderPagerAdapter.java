package com.nguyenvanhoa.book_app_reading.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.nguyenvanhoa.book_app_reading.Activity.Book_Detail_Activity;
import com.nguyenvanhoa.book_app_reading.Model.Slide_Show;
import com.nguyenvanhoa.book_app_reading.R;

import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Slide_Show> mList;

    public SliderPagerAdapter(Context context, List<Slide_Show> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.slide_item, null);
//        ImageView slideImg = view.findViewById(R.id.slide_img);
//        slideImg.setImageResource(mList.get(position).getFileUrl());
//        container.addView(view);

        View view = LayoutInflater.from(context).inflate(R.layout.slide_item, null);
        ImageView slideImg = view.findViewById(R.id.slide_img);
        Glide.with(context).load(mList.get(position).getFileUrl()).into(slideImg);
        container.addView(view);
        slideImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book_Detail_Activity.nameClass = getClass().toString();
                Intent intent = new Intent(context, Book_Detail_Activity.class);
                intent.putExtra("bookId", mList.get(position).getId());
                intent.putExtra("bookName", mList.get(position).getBookName());
                intent.putExtra("bookImg", mList.get(position).getFileUrl());
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
