package com.hust.service;

import com.hust.entity.ActivityEntity;
import com.hust.entity.VolunteerEntity;
import com.hust.model.VolunteerInputModel;
import com.hust.model.VolunteerOutPutModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VolunteerService {
    VolunteerEntity save(VolunteerInputModel volunteer,String username);
    void delete(long id);
    List<ActivityEntity> getActivities(long id);
    List<VolunteerOutPutModel> findAll();
    VolunteerOutPutModel findById(long id);
}
