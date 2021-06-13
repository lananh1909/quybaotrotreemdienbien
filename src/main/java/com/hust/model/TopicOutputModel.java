package com.hust.model;

import java.util.Date;

public class TopicOutputModel extends BaseModel{
    private String topicName;
    private long numAct;

    public TopicOutputModel(long id, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
        super(id, createdBy, createdDate, modifiedBy, modifiedDate);
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public long getNumAct() {
        return numAct;
    }

    public void setNumAct(long numAct) {
        this.numAct = numAct;
    }
}
