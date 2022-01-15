package com.nguyenvanhoa.book_app_reading.User.Activity.Filter;

import android.widget.Filter;

import com.nguyenvanhoa.book_app_reading.User.Adapter.AdapterBook;
import com.nguyenvanhoa.book_app_reading.User.Model.Book;

import java.util.ArrayList;

public class FilterBook extends Filter {

    ArrayList<Book> filterList;
    //adapter in which filter need to be implemented
    AdapterBook adapterBook;

    public FilterBook(ArrayList<Book> filterList, AdapterBook adapterBook) {
        this.filterList = filterList;
        this.adapterBook = adapterBook;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<Book> filteredModels = new ArrayList<>();
            for(int i = 0; i<filterList.size(); i++){
                if (filterList.get(i).getTitle().toUpperCase().contains(constraint) || filterList.get(i).getAuthor().toUpperCase().contains(constraint) || filterList.get(i).getCategoryId().toUpperCase().contains(constraint)) {
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        }else{
            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterBook.bookArrayList = (ArrayList<Book>)results.values;
    //notify changes
        adapterBook.notifyDataSetChanged();
    }
}
