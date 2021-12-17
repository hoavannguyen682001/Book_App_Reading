package com.nguyenvanhoa.book_app_reading.User.Home.Model;

public class ItemBookData {
//
//    private String name;
//    private String image;
//    private String categoryId;
//
//    public ItemBookData(String categoryId, String name, String image) {
//        this.name = name;
//        this.image = image;
//        this.categoryId = categoryId;
//    }
//
//
//    public ItemBookData() {
//    }
//
//    public String getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(String categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
    String categoryId, id, title,Image;

    public ItemBookData(String categoryId, String id, String title, String image) {
        this.categoryId = categoryId;
        this.id = id;
        this.title = title;
        Image = image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public ItemBookData() {
    }
}
