package com.hust.service.impl;

import com.hust.converter.Converter;
import com.hust.entity.ActivityEntity;
import com.hust.entity.BlogEntity;
import com.hust.entity.ListImage;
import com.hust.model.AddBlogInputModel;
import com.hust.model.BlogOutPutModel;
import com.hust.model.BlogPaging;
import com.hust.repo.*;
import com.hust.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    BlogRepo blogRepo;

    @Autowired
    ListImageRepo listImageRepo;

    @Autowired
    FileDBRepo fileDBRepo;

    @Autowired
    ActivityRepo activityRepo;

    @Override
    public BlogEntity save(AddBlogInputModel input) {
        BlogEntity blog = new BlogEntity();
        blog.setTitle(input.getTitle());
        blog.setContent(input.getContent());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        blog.setUser(userRepo.findByUsername(authentication.getName()));
        List<ListImage> imgs = new ArrayList<>();
        for(long i: input.getListImage()){
            ListImage img = new ListImage();
            img.setBlog(blog);
            img.setImage(fileDBRepo.findById(i));
            imgs.add(img);
        }
        blog.setImages(imgs);
        blog = blogRepo.save(blog);
        return blog;
    }

    @Override
    public BlogEntity save(AddBlogInputModel input, long id) {
        BlogEntity blog = blogRepo.findOneById(id);
        blog.setTitle(input.getTitle());
        blog.setContent(input.getContent());
        List<ListImage> list = listImageRepo.findByBlogId(id);
        listImageRepo.deleteAll(list);
        List<ListImage> imgs = new ArrayList<>();
        for(long i: input.getListImage()){
            ListImage img = new ListImage();
            img.setBlog(blog);
            img.setImage(fileDBRepo.findById(i));
            imgs.add(img);
        }
        blog.setImages(imgs);
        blog = blogRepo.save(blog);
        for(ListImage i: list){
            List<ActivityEntity> acts = activityRepo.findByImage(i.getImage());
            List<ListImage> re = listImageRepo.findByImageId(i.getImage().getId());
            if(re.size() == 0 && acts.size() == 0){
                fileDBRepo.delete(i.getImage());
            }
        }
        return blog;
    }

    @Override
    public void delete(long id) {
        List<ListImage> images = listImageRepo.findByBlogId(id);
        blogRepo.deleteById(id);
        for(ListImage i: images){
            List<ActivityEntity> acts = activityRepo.findByImage(i.getImage());
            List<ListImage> re = listImageRepo.findByImageId(i.getImage().getId());
            if(re.size() <= 1 && acts.size() == 0){
                fileDBRepo.delete(i.getImage());
            }
        }
    }

    @Override
    public BlogPaging findAll(Pageable pageable) {
        Page<BlogEntity> blogs = blogRepo.findAll(pageable);
        List<BlogEntity> list = blogs.getContent();
        List<BlogOutPutModel> out = new ArrayList<>();
        for(BlogEntity b: list){
            List<ListImage> img = listImageRepo.findByBlogId(b.getId());
            List<Long> id = new ArrayList<>();
            for(ListImage i: img){
                id.add(i.getImage().getId());
            }
            out.add(Converter.toOutputModel(b, id));
        }
        return new BlogPaging(blogs.getNumber(), blogs.getTotalElements(), blogs.getTotalPages(), out);
    }

    @Override
    public BlogPaging findAllByTitle(String title, Pageable pageable) {
        String a = "%x%";
        a = a.replaceAll("x", title.replaceAll(" ", "%"));
        Page<BlogEntity> blogs = blogRepo.findByTitleLikeIgnoreCase(a, pageable);
        List<BlogEntity> list = blogs.getContent();
        List<BlogOutPutModel> out = new ArrayList<>();
        for(BlogEntity b: list){
            List<ListImage> img = listImageRepo.findByBlogId(b.getId());
            List<Long> id = new ArrayList<>();
            for(ListImage i: img){
                id.add(i.getImage().getId());
            }
            out.add(Converter.toOutputModel(b, id));
        }
        return new BlogPaging(blogs.getNumber(), blogs.getTotalElements(), blogs.getTotalPages(), out);
    }

    @Override
    public BlogOutPutModel getBlog(long id) {
        BlogEntity blog = blogRepo.findOneById(id);
        List<ListImage> img = listImageRepo.findByBlogId(id);
        List<Long> imgs = new ArrayList<>();
        for(ListImage i: img){
            imgs.add(i.getImage().getId());
        }
        return Converter.toOutputModel(blog, imgs);
    }
}
