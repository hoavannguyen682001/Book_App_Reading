package com.nguyenvanhoa.book_app_reading.User.Home.Model;

import java.util.ArrayList;

public class ItemBookGroup {

//    private String headerTitle;
//    private ArrayList<ItemBookData> listItem;
//
//    public ItemBookGroup() {
//    }
//
//    public ItemBookGroup(String headerTitle, ArrayList<ItemBookData> listItem) {
//        this.headerTitle = headerTitle;
//        this.listItem = listItem;
//    }
//
//    public String getHeaderTitle() {
//        return headerTitle;
//    }
//
//    public void setHeaderTitle(String headerTitle) {
//        this.headerTitle = headerTitle;
//    }
//
//    public ArrayList<ItemBookData> getListItem() {
//        return listItem;
//    }
//
//    public void setListItem(ArrayList<ItemBookData> listItem) {
//        this.listItem = listItem;
//    }


    private String id;
    private ArrayList<ItemBookData> listItem;

    public ItemBookGroup() {
    }

    public ItemBookGroup(String id, ArrayList<ItemBookData> listItem) {
        this.id = id;
        this.listItem = listItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<ItemBookData> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<ItemBookData> listItem) {
        this.listItem = listItem;
    }
}
