package com.hust.converter;

import com.hust.entity.*;
import com.hust.model.*;

import java.util.List;

public class Converter {
    public static ActivityOutputModel toOutPutModel(ActivityEntity act){
        return new ActivityOutputModel(act.getId(), act.getCreatedBy(), act.getCreatedDate(),
                act.getModifiedBy(), act.getModifiedDate(), act.getTitle(), act.getContent(),
                act.getLocation(), act.getCommune(), act.getTopic(), act.getUser(), act.getFromDate(),
                act.getToDate(), act.getImage(), act.getAttendees().size());
    }
    public static BlogOutPutModel toOutputModel(BlogEntity blog, List<Long> images){
        BlogOutPutModel out = new BlogOutPutModel(blog.getId(), blog.getCreatedBy(), blog.getCreatedDate(), blog.getModifiedBy(), blog.getModifiedDate(),
                blog.getTitle(), blog.getContent(), images, blog.getUser());
        return out;
    }

    public static UserOutputModel toOutputModel(UserEntity user, long num){
        UserOutputModel out = new UserOutputModel(user.getId(), user.getCreatedBy(), user.getCreatedDate(), user.getModifiedBy(), user.getModifiedDate());
        out.setUsername(user.getUsername());
        out.setEmail(user.getEmail());
        if(user.getVolunteer() !=  null){
            out.setVolunteer(Converter.toOutputModel(user.getVolunteer(), num));
        }
        out.setRole(user.getRole());
        return out;
    }

    public static VolunteerOutPutModel toOutputModel(VolunteerEntity vol, long num){
        VolunteerOutPutModel volunteer = new VolunteerOutPutModel();
        volunteer.setFullName(vol.getFullName());
        volunteer.setBirthDate(vol.getBirthDate());
        volunteer.setCommune(vol.getCommune());
        volunteer.setPhoneNum(vol.getPhoneNum());
        volunteer.setNumAttend(num);
        volunteer.setGender(vol.getGender());
        return volunteer;
    }

    public static TopicOutputModel toOutputModel(TopicEntity topic, long numAct){
        TopicOutputModel out = new TopicOutputModel(topic.getId(), topic.getCreatedBy(), topic.getCreatedDate(), topic.getModifiedBy(), topic.getModifiedDate());
        out.setTopicName(topic.getTopicName());
        out.setNumAct(numAct);
        return out;
    }
}
