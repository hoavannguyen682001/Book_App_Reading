package com.nguyenvanhoa.book_app_reading.Model;

import java.util.List;

public class AllCategory {
    private String Categorytitle;
    private Integer CategoryId;
    private List<Book> bookList = null;

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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public AllCategory(Integer categoryId, String categorytitle, List<Book> bookList) {
        Categorytitle = categorytitle;
        CategoryId = categoryId;
        this.bookList = bookList;
    }
}
