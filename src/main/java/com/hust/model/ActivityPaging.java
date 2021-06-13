package com.hust.model;

import java.util.List;

public class ActivityPaging {
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private List<ActivityOutputModel> activities;

    public ActivityPaging(int currentPage, long totalItems, int totalPages, List<ActivityOutputModel> activities) {
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.activities = activities;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<ActivityOutputModel> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityOutputModel> activities) {
        this.activities = activities;
    }
}
