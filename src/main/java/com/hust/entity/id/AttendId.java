package com.hust.entity.id;

import java.io.Serializable;

public class AttendId implements Serializable {
    private long activity;
    private long volunteer;

    public AttendId(){

    }

    public AttendId(long activity, long volunteer) {
        this.activity = activity;
        this.volunteer = volunteer;
    }

    public long getActivity() {
        return activity;
    }

    public void setActivity(long activity) {
        this.activity = activity;
    }

    public long getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(long volunteer) {
        this.volunteer = volunteer;
    }
}
