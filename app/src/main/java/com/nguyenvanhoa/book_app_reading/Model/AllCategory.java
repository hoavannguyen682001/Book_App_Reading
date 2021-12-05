package com.nguyenvanhoa.book_app_reading.Model;

import java.util.List;

public class AllCategory {
    private String Categorytitle;
    private Integer CategoryId;
    private List<Book2> bookList = null;

     String id, Catogery, uid;
     Long timestamp;

    public AllCategory(String id, String Catogery, String uid, Long timestamp) {
        this.id = id;
        this.Catogery = Catogery;
        this.uid = uid;
        this.timestamp = timestamp;
    }

    public AllCategory() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return Catogery;
    }

    public void setCategory(String Catogery) {
        this.Catogery = Catogery;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCategorytitle() {
        return Categorytitle;
    }

    public void setCategorytitle(String categorytitle) {
        Categorytitle = categorytitle;
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer categoryId) {
        CategoryId = categoryId;
    }

    public List<Book2> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book2> bookList) {
        this.bookList = bookList;
    }

    public AllCategory(Integer categoryId, String categorytitle, List<Book2> bookList) {
        Categorytitle = categorytitle;
        CategoryId = categoryId;
        this.bookList = bookList;
    }
}
