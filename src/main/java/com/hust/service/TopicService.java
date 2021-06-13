package com.hust.service;

import com.hust.entity.TopicEntity;
import com.hust.model.TopicOutputModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicService {
    List<TopicOutputModel> findAll();
    TopicOutputModel save(String name);
    TopicOutputModel save(String name, long id);
    void delete(long id);
}
