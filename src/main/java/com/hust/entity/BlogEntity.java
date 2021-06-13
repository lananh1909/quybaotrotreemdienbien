package com.hust.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"blogs\"")

public class BlogEntity extends BaseEntity{
    @Column(columnDefinition="TEXT")
    private String title;

    @Column(columnDefinition="TEXT")
    private String Content;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ListImage> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "posted_by")
    private UserEntity user;

    public List<ListImage> getImages() {
        return images;
    }

    public void setImages(List<ListImage> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
