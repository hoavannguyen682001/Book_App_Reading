package com.nguyenvanhoa.book_app_reading.User.Test.Model;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String title;
    private String categoryId;
    private String code;
    private String publisher;
    private String uid;
    private String url;
    private long borrowsCount;
    private long quantity;
    private long timestamp;
    private long payDay;

    private String Image;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public long getPayDay() {
        return payDay;
    }

    public void setPayDay(long payDay) {
        this.payDay = payDay;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Book() {
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getBorrowsCount() {
        return borrowsCount;
    }

    public void setBorrowsCount(int borrowsCount) {
        this.borrowsCount = borrowsCount;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book(String categoryId, String id, String title, String image) {
        this.categoryId = categoryId;
        this.id = id;
        this.title = title;
        image = image;
    }
}
