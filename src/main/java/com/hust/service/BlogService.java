package com.hust.service;

import com.hust.entity.BlogEntity;
import com.hust.model.AddBlogInputModel;
import com.hust.model.BlogOutPutModel;
import com.hust.model.BlogPaging;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    BlogEntity save(AddBlogInputModel input);
    BlogEntity save(AddBlogInputModel input, long id);
    void delete(long id);
    BlogPaging findAll(Pageable pageable);
    BlogPaging findAllByTitle(String title, Pageable pageable);
    BlogOutPutModel getBlog(long id);
}
