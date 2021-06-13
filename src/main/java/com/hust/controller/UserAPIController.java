package com.hust.controller;

import com.hust.entity.UserEntity;
import com.hust.model.AddUserInputModel;
import com.hust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserAPIController {
    @Autowired
    private UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String username,
                                         @RequestParam(defaultValue = "0") int role_id,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        if(username == null){
            return ResponseEntity.ok().body(userService.findAll(role_id, pageable));
        } else {
            return ResponseEntity.ok().body(userService.findAll(username, role_id, pageable));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody AddUserInputModel input, @PathVariable("id") long id){
        UserEntity user = userService.save(input, id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@RequestBody long id){
        try{
            userService.delete(id);
            return ResponseEntity.ok().body("SUCCESS");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Không thể xóa người dùng này!");
        }

    }

    @PostMapping("/get-blogs")
    public ResponseEntity<?> getBlogs(@RequestBody long userId){
        return ResponseEntity.ok().body(userService.getBlogs(userId));
    }
}
