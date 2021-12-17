package com.nguyenvanhoa.book_app_reading.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MySliderAdapter extends SliderAdapter {
    private List<String> imageList;
    private Context context;
    int i = 0;
    public MySliderAdapter(Context context, List<String> imageList) {
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        imageSlideViewHolder.bindImageSlide(imageList.get(position));
    }
}
