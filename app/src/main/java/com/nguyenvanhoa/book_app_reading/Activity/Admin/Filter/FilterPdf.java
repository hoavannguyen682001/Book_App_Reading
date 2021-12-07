package com.nguyenvanhoa.book_app_reading.Activity.Admin.Filter;

import android.widget.Filter;


import com.nguyenvanhoa.book_app_reading.Activity.Admin.Adapter.AdapterPdf;
import com.nguyenvanhoa.book_app_reading.Activity.Admin.Models.PdfModel;

import java.util.ArrayList;

public class FilterPdf extends Filter {

    ArrayList<PdfModel> filterList;
    //adapter in which filter need to be implemented
    AdapterPdf adapterPdf;

    public FilterPdf(ArrayList<PdfModel> filterList, AdapterPdf adapterPdf) {
        this.filterList = filterList;
        this.adapterPdf = adapterPdf;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<PdfModel> filteredModels = new ArrayList<>();
            for(int i = 0; i<filterList.size(); i++){
                if (filterList.get(i).getTitle().toUpperCase().contains(constraint)) {
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
        adapterPdf.pdfArrayList = (ArrayList<PdfModel>)results.values;
//notify cahnges
        adapterPdf.notifyDataSetChanged();
    }
}
