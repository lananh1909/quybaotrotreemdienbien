package com.hust.model;

import java.util.List;

public class DashBoardModel {
    private List<StatisticalOutput> user;
    private List<StatisticalOutput> attend;
    private long numUser;
    private long numBlog;
    private long numActivity;

    public DashBoardModel(List<StatisticalOutput> user, List<StatisticalOutput> attend, long numUser, long numBlog, long numActivity) {
        this.user = user;
        this.attend = attend;
        this.numUser = numUser;
        this.numBlog = numBlog;
        this.numActivity = numActivity;
    }

    public List<StatisticalOutput> getUser() {
        return user;
    }

    public void setUser(List<StatisticalOutput> user) {
        this.user = user;
    }

    public List<StatisticalOutput> getAttend() {
        return attend;
    }

    public void setAttend(List<StatisticalOutput> attend) {
        this.attend = attend;
    }

    public long getNumUser() {
        return numUser;
    }

    public void setNumUser(long numUser) {
        this.numUser = numUser;
    }

    public long getNumBlog() {
        return numBlog;
    }

    public void setNumBlog(long numBlog) {
        this.numBlog = numBlog;
    }

    public long getNumActivity() {
        return numActivity;
    }

    public void setNumActivity(long numActivity) {
        this.numActivity = numActivity;
    }
}
