package com.hust.model;

import com.hust.entity.UserEntity;

import java.util.Date;
import java.util.List;

public class BlogOutPutModel extends BaseModel{
    private String title;
    private String content;
    private List<Long> images;
    private UserEntity user;

    public BlogOutPutModel(long id, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String title, String content, List<Long> images, UserEntity user) {
        super(id, createdBy, createdDate, modifiedBy, modifiedDate);
        this.title = title;
        this.content = content;
        this.images = images;
        this.user = user;
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

    public List<Long> getImages() {
        return images;
    }

    public void setImages(List<Long> images) {
        this.images = images;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
