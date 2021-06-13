package com.hust.model;

import com.hust.address.entity.Commune;
import com.hust.entity.FileDB;
import com.hust.entity.TopicEntity;
import com.hust.entity.UserEntity;

import java.util.Date;

public class ActivityOutputModel extends BaseModel{
    private String title;
    private String content;
    private String location;
    private Commune commune;
    private TopicEntity topic;
    private UserEntity user;
    private Date fromDate;
    private Date toDate;
    private FileDB image;
    private int numVolunteer;

    public ActivityOutputModel(long id, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String title, String content, String location, Commune commune, TopicEntity topic, UserEntity user, Date fromDate, Date toDate, FileDB image, int numVolunteer) {
        super(id, createdBy, createdDate, modifiedBy, modifiedDate);
        this.title = title;
        this.content = content;
        this.location = location;
        this.commune = commune;
        this.topic = topic;
        this.user = user;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.image = image;
        this.numVolunteer = numVolunteer;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public FileDB getImage() {
        return image;
    }

    public void setImage(FileDB image) {
        this.image = image;
    }

    public int getNumVolunteer() {
        return numVolunteer;
    }

    public void setNumVolunteer(int numVolunteer) {
        this.numVolunteer = numVolunteer;
    }
}
