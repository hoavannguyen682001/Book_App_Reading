package com.nguyenvanhoa.book_app_reading.Model;

public class TopAuthor {
    private int imgAuthor;
    private String name;

//    public TopAuthor(int imgAuthor) {
//        this.imgAuthor = imgAuthor;
//    }

    public TopAuthor(int imgAuthor, String name) {
        this.imgAuthor = imgAuthor;
        this.name = name;
    }

    public int getImgAuthor() {
        return imgAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgAuthor(int imgAuthor) {
        this.imgAuthor = imgAuthor;
    }
}
