package com.nguyenvanhoa.book_app_reading.Model;

public class Slide_Show {
    private int Id;
    private String bookName;
    private Integer fileUrl;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(Integer fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Slide_Show(int id, String bookName, Integer fileUrl) {
        Id = id;
        this.bookName = bookName;
        this.fileUrl = fileUrl;
    }
}
