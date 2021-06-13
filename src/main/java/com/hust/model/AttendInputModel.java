package com.hust.model;

public class AttendInputModel {
    private long activityId;
    private String skill;
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
