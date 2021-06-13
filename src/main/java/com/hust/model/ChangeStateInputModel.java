package com.hust.model;

public class ChangeStateInputModel {
    private long actId;
    private long volId;
    private int state;
//state 0: Cho phe duyet; state 1: Da phe duyet; state 2: Khong xac nhan
    public long getActId() {
        return actId;
    }

    public void setActId(long actId) {
        this.actId = actId;
    }

    public long getVolId() {
        return volId;
    }

    public void setVolId(long volId) {
        this.volId = volId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
