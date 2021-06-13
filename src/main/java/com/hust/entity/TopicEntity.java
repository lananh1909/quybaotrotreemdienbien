package com.hust.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"topic\"")
public class TopicEntity extends BaseEntity{
    @Column
    private String topicName;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ActivityEntity> activities = new ArrayList<>();

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityEntity> activities) {
        this.activities = activities;
    }
}
