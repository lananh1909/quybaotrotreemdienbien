package com.hust.service.impl;

import com.hust.converter.Converter;
import com.hust.entity.BlogEntity;
import com.hust.entity.UserEntity;
import com.hust.model.*;
import com.hust.repo.*;
import com.hust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    AttendRepo attendRepo;
    @Autowired
    CustomRepo customRepo;
    @Autowired
    ActivityRepo activityRepo;
    @Autowired
    BlogRepo blogRepo;

    @Override
    public UserEntity save(AddUserInputModel user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername().toLowerCase());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail().toLowerCase());
        userEntity.setRole(roleRepo.findOneById(user.getRoleId()));
        userEntity = userRepo.save(userEntity);
        return userEntity;
    }

    @Override
    public UserPaging findAll(long role_id, Pageable pageable) {
        Page<UserEntity> users;
        if(role_id == 0){
            users = userRepo.findAll(pageable);
        } else {
            users = userRepo.findByRoleId(role_id, pageable);
        }
        List<UserEntity> user = users.getContent();
        List<UserOutputModel> out = new ArrayList<>();
        for(UserEntity u: user){
            long num = 0;
            if(u.getVolunteer() != null){
                num = attendRepo.countByVolunteerId(u.getId());
            }
            out.add(Converter.toOutputModel(u, num));
        }
        return new UserPaging(users.getNumber(), users.getTotalElements(), users.getTotalPages(), out);
    }

    @Override
    public UserPaging findAll(String username, long role_id, Pageable pageable) {
        String a = "%x%";
        a = a.replaceAll("x", username.replaceAll(" ", "%"));
        Page<UserEntity> users;
        if(role_id == 0){
            users = userRepo.findByUsernameLikeIgnoreCase(a, pageable);
        } else {
            users = userRepo.findByUsernameLikeIgnoreCaseAndRoleId(a, role_id, pageable);
        }
        List<UserEntity> user = users.getContent();
        List<UserOutputModel> out = new ArrayList<>();
        for(UserEntity u: user){
            long num = 0;
            if(u.getVolunteer() != null){
                num = attendRepo.countByVolunteerId(u.getId());
            }
            out.add(Converter.toOutputModel(u, num));
        }
        return new UserPaging(users.getNumber(), users.getTotalElements(), users.getTotalPages(), out);
    }

    @Override
    public UserEntity save(AddUserInputModel input, long id) {
        UserEntity user = userRepo.getOne(id);
        user.setUsername(input.getUsername().toLowerCase());
        user.setPassword(input.getPassword());
        user.setEmail(input.getEmail().toLowerCase());
        user.setRole(roleRepo.findOneById(input.getRoleId()));
        user = userRepo.save(user);
        return user;
    }

    @Override
    public void delete(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<BlogEntity> getBlogs(long userId) {
        UserEntity user = userRepo.findById(userId);
        return user.getBlogs();
    }

    @Override
    public UserOutputModel findById(long id) {
        UserEntity user = userRepo.findById(id);
        int num = 0;
        if(user.getVolunteer() != null){
            num = user.getVolunteer().getAttends().size();
        }
        return Converter.toOutputModel(user, num);
    }

    @Override
    public DashBoardModel getStatistical() {
        List<StatisticalOutput> user = customRepo.getCount();
        List<StatisticalOutput> atted = customRepo.getCountAttend();
        long numUser = userRepo.count();
        long numActivity = activityRepo.count();
        long numBlog = blogRepo.count();
        return new DashBoardModel(user, atted, numUser, numBlog, numActivity);
    }
}
