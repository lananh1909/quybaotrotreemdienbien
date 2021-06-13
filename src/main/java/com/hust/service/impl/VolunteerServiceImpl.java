package com.hust.service.impl;

import com.hust.address.repo.CommuneRepo;
import com.hust.converter.Converter;
import com.hust.entity.ActivityEntity;
import com.hust.entity.AttendEntity;
import com.hust.entity.UserEntity;
import com.hust.entity.VolunteerEntity;
import com.hust.model.VolunteerInputModel;
import com.hust.model.VolunteerOutPutModel;
import com.hust.repo.AttendRepo;
import com.hust.repo.UserRepo;
import com.hust.repo.VolunteerRepo;
import com.hust.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    CommuneRepo communeRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    VolunteerRepo volunteerRepo;

    @Autowired
    AttendRepo attendRepo;

    @Override
    public VolunteerEntity save(VolunteerInputModel volunteer, String username) {
        UserEntity user = userRepo.findByUsername(username);
        VolunteerEntity vol = volunteerRepo.findByUser(user);
        if(vol == null){
            vol = new VolunteerEntity();
        }
        vol.setFullName(volunteer.getFullName());
        vol.setBirthDate(volunteer.getBirthDate());
        vol.setPhoneNum(volunteer.getPhoneNum());
        vol.setCommune(communeRepo.findByCommuneId(volunteer.getCommuneId()));
        vol.setUser(user);
        vol.setGender(volunteer.getGender());
        vol = volunteerRepo.save(vol);
        return vol;
    }

    @Override
    public List<VolunteerOutPutModel> findAll() {
        List<VolunteerEntity> volunteers = volunteerRepo.findAll();
        List<VolunteerOutPutModel> vols = new ArrayList<>();
        for(VolunteerEntity v: volunteers){
            long num = attendRepo.countByVolunteerId(v.getId());
            vols.add(Converter.toOutputModel(v, num));
        }
        return vols;
    }

    @Override
    public VolunteerOutPutModel findById(long id) {
        VolunteerEntity volunteer = volunteerRepo.findByUserId(id);
        if(volunteer != null) {
            long num = attendRepo.countByVolunteerId(volunteer.getId());
            return Converter.toOutputModel(volunteer, num);
        }
        return null;
    }


    @Override
    public void delete(long id) {
        volunteerRepo.deleteByUserId(id);
    }

    @Override
    public List<ActivityEntity> getActivities(long id) {
        List<AttendEntity> attends = attendRepo.findByVolunteerId(id);
        List<ActivityEntity> activities = new ArrayList<>();
        for(AttendEntity act: attends){
            activities.add(act.getActivity());
        }
        return activities;
    }
}
