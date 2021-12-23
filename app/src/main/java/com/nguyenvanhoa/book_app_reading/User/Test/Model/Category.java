package com.nguyenvanhoa.book_app_reading.User.Test.Model;

public class Category {
    private String id;
    private String category;
    private long timestamp;
    private String uid;

    public Category() {}

    public Category(String id, String category, long timestamp, String uid) {
        this.id = id;
        this.category = category;
        this.timestamp = timestamp;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

