package com.hust.service;

import com.hust.entity.BlogEntity;
import com.hust.entity.UserEntity;
import com.hust.model.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserEntity save(AddUserInputModel user);
    UserPaging findAll(long role_id, Pageable pageable);
    UserPaging findAll(String username, long role_id, Pageable pageable);
    UserEntity save(AddUserInputModel input, long id);
    void delete(long id);
    List<BlogEntity> getBlogs(long userId);
    UserOutputModel findById(long id);

    DashBoardModel getStatistical();
}
