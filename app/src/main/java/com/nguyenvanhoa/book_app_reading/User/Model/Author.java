package com.nguyenvanhoa.book_app_reading.User.Model;

public class Author {
    private String authorId, authorName, authorImage;

    public Author() {
    }

    public Author(String authorId, String authorName, String authorImage) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorImage = authorImage;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }
}
