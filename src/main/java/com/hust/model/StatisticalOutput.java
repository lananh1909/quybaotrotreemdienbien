package com.hust.model;

public class StatisticalOutput {
    private long month;
    private long count;

    public StatisticalOutput(long month, long count) {
        this.month = month;
        this.count = count;
    }

    public long getMonth() {
        return month;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
