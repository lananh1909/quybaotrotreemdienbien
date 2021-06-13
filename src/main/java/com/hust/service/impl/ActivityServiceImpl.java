package com.hust.service.impl;

import com.hust.address.repo.CommuneRepo;
import com.hust.converter.Converter;
import com.hust.entity.ActivityEntity;
import com.hust.entity.AttendEntity;
import com.hust.entity.ListImage;
import com.hust.entity.VolunteerEntity;
import com.hust.model.ActivityInputModel;
import com.hust.model.ActivityOutputModel;
import com.hust.model.ActivityPaging;
import com.hust.repo.*;
import com.hust.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityRepo activityRepo;

    @Autowired
    CommuneRepo communeRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    TopicRepo topicRepo;

    @Autowired
    AttendRepo attendRepo;

    @Autowired
    FileDBRepo fileDBRepo;

    @Autowired
    ListImageRepo listImageRepo;

    @Override
    public ActivityEntity save(ActivityInputModel input) {
        ActivityEntity act = new ActivityEntity();
        act.setTitle(input.getTitle());
        act.setContent(input.getContent());
        act.setLocation(input.getLocation());
        act.setCommune(communeRepo.findByCommuneId(input.getCommuneId()));
        act.setTopic(topicRepo.findById(input.getTopicId()));
        act.setUser(userRepo.findById(input.getUserId()));
        act.setImage(fileDBRepo.findById(input.getFileId()));
        act.setFromDate(input.getFromDate());
        act.setToDate(input.getToDate());
        act = activityRepo.save(act);
        return act;
    }

    @Override
    public ActivityEntity save(ActivityInputModel input, long id) {
        ActivityEntity act = activityRepo.findById(id);
        act.setTitle(input.getTitle());
        act.setContent(input.getContent());
        act.setLocation(input.getLocation());
        act.setCommune(communeRepo.findByCommuneId(input.getCommuneId()));
        act.setTopic(topicRepo.findById(input.getTopicId()));
        act.setUser(userRepo.findById(input.getUserId()));
        act.setImage(fileDBRepo.findById(input.getFileId()));
        act.setFromDate(input.getFromDate());
        act.setToDate(input.getToDate());
        act = activityRepo.save(act);
        return act;
    }

    @Override
    public void delete(long id) {
        ActivityEntity ac = activityRepo.findById(id);
        activityRepo.delete(ac);
        List<ActivityEntity> acts = activityRepo.findByImage(ac.getImage());
        List<ListImage> imgs = listImageRepo.findByImageId(ac.getImage().getId());
        if(acts.size() == 0 && imgs.size() == 0){
            fileDBRepo.delete(ac.getImage());
        }
    }

    @Override
    public List<VolunteerEntity> getVolunteers(long id) {
        List<AttendEntity> attends = attendRepo.findByActivityId(id);
        List<VolunteerEntity> volunteers = new ArrayList<>();
        for(AttendEntity at: attends){
            volunteers.add(at.getVolunteer());
        }
        return volunteers;
    }

    @Override
    public ActivityOutputModel getActiviy(long id) {
        return Converter.toOutPutModel(activityRepo.findById(id));
    }

    @Override
    public ActivityPaging findAll(long topic, String districtId, Pageable pageable) {
        Page<ActivityEntity> activities;
        if(topic != 0){
            activities = activityRepo.findByTopicId(topic, pageable);
        } else if(!districtId.equals("0")){
            activities = activityRepo.findByCommune_District_DistrictId(districtId, pageable);
        } else {
            activities = activityRepo.findAll(pageable);
        }
        List<ActivityEntity> acts = activities.getContent();
        List<ActivityOutputModel> out = new ArrayList<>();
        for (ActivityEntity a : acts) {
            out.add(Converter.toOutPutModel(a));
        }
        return new ActivityPaging(activities.getNumber(), activities.getTotalElements(), activities.getTotalPages(), out);
    }

    @Override
    public ActivityPaging findAllByTitle(String title, long topic, String districtId, Pageable pageable) {
        String a = "%x%";
        a = a.replaceAll("x", title.replaceAll(" ", "%"));
        Page<ActivityEntity> activities;
        if(topic != 0){
            activities = activityRepo.findByTitleLikeIgnoreCaseAndTopicId(a, topic, pageable);
        }else if(!districtId.equals("0")){
            activities = activityRepo.findByTitleLikeIgnoreCaseAndCommune_District_DistrictId(a, districtId, pageable);
        }
        else {
            activities = activityRepo.findByTitleLikeIgnoreCase(a, pageable);
        }
        List<ActivityEntity> acts = activities.getContent();
        List<ActivityOutputModel> out = new ArrayList<>();
        for (ActivityEntity act : acts) {
            out.add(Converter.toOutPutModel(act));
        }
        return new ActivityPaging(activities.getNumber(), activities.getTotalElements(), activities.getTotalPages(), out);
    }

    @Override
    public List<ActivityOutputModel> getFollowing() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = LocalDateTime.now().format(formatter);
        List<ActivityEntity> activityEntities = activityRepo.findByFromDateAfter(new Date());
        List<ActivityOutputModel> out = new ArrayList<>();
        for(ActivityEntity a: activityEntities){
            out.add(Converter.toOutPutModel(a));
        }
        return out;
    }
}
