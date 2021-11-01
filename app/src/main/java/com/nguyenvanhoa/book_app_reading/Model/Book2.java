package com.nguyenvanhoa.book_app_reading.Model;

public class Book2 {
    private String name;
    private String author;
    private String date;
    private String category;
    private int img;

    public Book2(String name, String author, String date, String category, int img) {
        this.name = name;
        this.author = author;
        this.date = date;
        this.category = category;
        this.img = img;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public int getImg() { return img; }

    public void setImg(int img) { this.img = img; }
}
