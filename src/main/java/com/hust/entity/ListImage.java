package com.hust.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hust.entity.id.ListImageId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "\"list_image\"")
@IdClass(ListImageId.class)
@EntityListeners(AuditingEntityListener.class)
public class ListImage {
    @Id
    @ManyToOne
    @JoinColumn(name = "blog_id")
    @JsonIgnore
    private BlogEntity blog;

    @Id
    @ManyToOne
    @JoinColumn(name = "image_id")
    private FileDB image;

    public BlogEntity getBlog() {
        return blog;
    }

    public void setBlog(BlogEntity blog) {
        this.blog = blog;
    }

    public FileDB getImage() {
        return image;
    }

    public void setImage(FileDB image) {
        this.image = image;
    }
}
