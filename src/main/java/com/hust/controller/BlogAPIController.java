package com.hust.controller;

import com.hust.entity.BlogEntity;
import com.hust.model.AddBlogInputModel;
import com.hust.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/blog")
public class BlogAPIController {
    @Autowired
    BlogService blogService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createBlog(Principal principal, @RequestBody AddBlogInputModel input){
        BlogEntity blog = blogService.save(input);
        return ResponseEntity.ok().body(blog);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getBlogByPage(@RequestParam(required = false) String title,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        if(title != null){
            return ResponseEntity.ok().body(blogService.findAllByTitle(title, pageable));
        } else {
            return ResponseEntity.ok().body(blogService.findAll(pageable));
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> upDateBlog(@RequestBody AddBlogInputModel input, @PathVariable("id") long id){
        BlogEntity blog = blogService.save(input, id);
        return ResponseEntity.ok().body(blog);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBlog(@RequestBody long id){
        blogService.delete(id);
        return ResponseEntity.ok().body("Xoa thanh cong");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBlog(@PathVariable("id") long id){
        return ResponseEntity.ok().body(blogService.getBlog(id));
    }
}
