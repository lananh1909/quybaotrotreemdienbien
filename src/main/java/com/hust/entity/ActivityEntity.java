package com.hust.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hust.address.entity.Commune;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"activity\"")
public class ActivityEntity extends BaseEntity{
    @Column(columnDefinition="TEXT")
    private String title;

    @Column(columnDefinition="TEXT")
    private String content;

    @Column(columnDefinition="TEXT")
    private String location;

    @ManyToOne
    @JoinColumn(name = "commune_id")
    private Commune commune;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    @ManyToOne
    @JoinColumn(name = "posted_by")
    private UserEntity user;

    @Column
    private Date fromDate;

    @Column
    private Date toDate;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AttendEntity> attendees = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image", referencedColumnName = "id")
    private FileDB image;

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

    public List<AttendEntity> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<AttendEntity> attendees) {
        this.attendees = attendees;
    }
}
