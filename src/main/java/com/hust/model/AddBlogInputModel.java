package com.hust.model;

import java.util.List;

public class AddBlogInputModel {
    private String title;
    private String content;
    private List<Long> listImage;

    public List<Long> getListImage() {
        return listImage;
    }

    public void setListImage(List<Long> listImage) {
        this.listImage = listImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
