package com.hust.service.impl;

import com.hust.converter.Converter;
import com.hust.entity.TopicEntity;
import com.hust.model.TopicOutputModel;
import com.hust.repo.ActivityRepo;
import com.hust.repo.TopicRepo;
import com.hust.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepo topicRepo;
    @Autowired
    ActivityRepo activityRepo;

    @Override
    public List<TopicOutputModel> findAll() {
        List<TopicEntity> topics = topicRepo.findAll();
        List<TopicOutputModel> out = new ArrayList<>();
        for(TopicEntity t: topics){
            out.add(Converter.toOutputModel(t, activityRepo.countByTopicId(t.getId())));
        }
        return out;
    }

    @Override
    public TopicOutputModel save(String name) {
        TopicEntity topic = new TopicEntity();
        topic.setTopicName(name);
        topic = topicRepo.save(topic);
        return Converter.toOutputModel(topic, 0);
    }

    @Override
    public TopicOutputModel save(String name, long id) {
        TopicEntity topic = topicRepo.findById(id);
        topic.setTopicName(name);
        topic = topicRepo.save(topic);
        return Converter.toOutputModel(topic, activityRepo.countByTopicId(id));
    }

    @Override
    public void delete(long id) {
        topicRepo.deleteById(id);
    }
}
