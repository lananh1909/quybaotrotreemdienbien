package com.hust.entity.id;

import java.io.Serializable;

public class ListImageId  implements Serializable {
    private long blog;
    private long image;

    public long getBlog() {
        return blog;
    }

    public void setBlog(long blog) {
        this.blog = blog;
    }

    public long getImage() {
        return image;
    }

    public void setImage(long image) {
        this.image = image;
    }
}
