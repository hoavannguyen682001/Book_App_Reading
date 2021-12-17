package com.nguyenvanhoa.book_app_reading.User.Home.Model;

public class ItemData {

    private String name;
    private String image;
    private String categoryId;

    public ItemData(String categoryId, String name, String image) {
        this.name = name;
        this.image = image;
        this.categoryId = categoryId;
    }


    public ItemData() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
