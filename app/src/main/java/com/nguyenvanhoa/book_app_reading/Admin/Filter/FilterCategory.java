package com.nguyenvanhoa.book_app_reading.Admin.Filter;

import android.widget.Filter;


import com.nguyenvanhoa.book_app_reading.Admin.Adapter.AdapterCategory;
import com.nguyenvanhoa.book_app_reading.Admin.Models.Category;

import java.util.ArrayList;

public class FilterCategory extends Filter {

    ArrayList<Category> filterList;
    //adapter in which filter need to be implemented
    AdapterCategory adapterCategory;

    public FilterCategory(ArrayList<Category> filterList, AdapterCategory adapterCategory) {
        this.filterList = filterList;
        this.adapterCategory = adapterCategory;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<Category> filteredModels = new ArrayList<>();
            for(int i = 0; i<filterList.size(); i++){
                if (filterList.get(i).getCategory().toUpperCase().contains(constraint)) {
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
        adapterCategory.categoryArrayList = (ArrayList<Category>)results.values;
//notify cahnges
        adapterCategory.notifyDataSetChanged();
    }
}
