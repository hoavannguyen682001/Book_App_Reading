package com.nguyenvanhoa.book_app_reading.User.Model;

public class Book {
    String uid, id, title, description, categoryId, url, author, Image;
    Long timestamp, viewscount, downloadsCount;
    boolean favorite;
    public Book() {
    }

    public Book(String uid, String id, String title, String description, String categoryId, String url, String author, String image, Long timestamp, Long viewscount, Long downloadsCount, boolean favorite) {
        this.uid = uid;
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.url = url;
        this.author = author;
        this.Image = image;
        this.timestamp = timestamp;
        this.viewscount = viewscount;
        this.downloadsCount = downloadsCount;
        this.favorite = favorite;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getViewscount() {
        return viewscount;
    }

    public void setViewscount(Long viewscount) {
        this.viewscount = viewscount;
    }

    public Long getDownloadsCount() {
        return downloadsCount;
    }

    public void setDownloadsCount(Long downloadsCount) {
        this.downloadsCount = downloadsCount;
    }
}
