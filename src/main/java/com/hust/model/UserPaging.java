package com.hust.model;

import java.util.List;

public class UserPaging {
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private List<UserOutputModel> users;

    public UserPaging(int currentPage, long totalItems, int totalPages, List<UserOutputModel> users) {
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.users = users;
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

    public List<UserOutputModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserOutputModel> users) {
        this.users = users;
    }
}
