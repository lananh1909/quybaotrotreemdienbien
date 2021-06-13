package com.hust.model;

import com.hust.entity.BlogEntity;

import java.util.List;

public class BlogPaging {
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private List<BlogOutPutModel> blogs;

    public BlogPaging(int currentPage, long totalItems, int totalPages, List<BlogOutPutModel> blogs) {
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.blogs = blogs;
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

    public List<BlogOutPutModel> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogOutPutModel> blogs) {
        this.blogs = blogs;
    }
}
