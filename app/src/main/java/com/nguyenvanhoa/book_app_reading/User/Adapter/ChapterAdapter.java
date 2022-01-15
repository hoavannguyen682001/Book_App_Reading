package com.nguyenvanhoa.book_app_reading.User.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nguyenvanhoa.book_app_reading.R;
import com.nguyenvanhoa.book_app_reading.User.Model.Chapter;

import java.util.ArrayList;

public class ChapterAdapter extends BaseAdapter {
    private ArrayList<Chapter> arrayList;
    private Context context;
    private int layout;

    public ChapterAdapter(ArrayList<Chapter> arrayList, Context context, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);

            holder.txtChapter = view.findViewById(R.id.txtChap);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.txtChapter.setText("Chap "+(i+1) +": "+ arrayList.get(i).getTitle()) ;

        return view;

    }

    public class ViewHolder{
        TextView txtChapter;
    }
}
