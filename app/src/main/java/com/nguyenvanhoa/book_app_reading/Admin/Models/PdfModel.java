package com.nguyenvanhoa.book_app_reading.Admin.Models;

public class PdfModel {
    String uid, id, title, description, categoryId, url;
    Long timestamp, viewscount, downloadsCount;

    public PdfModel() {
    }

    public PdfModel(String uid, String id, String title, String description, String categoryId, String url, Long timestamp, Long viewscount, Long downloadsCount) {
        this.uid = uid;
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.url = url;
        this.timestamp = timestamp;
        this.viewscount = viewscount;
        this.downloadsCount = downloadsCount;
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
}
