package com.hust.service.impl;

import com.hust.entity.ActivityEntity;
import com.hust.entity.AttendEntity;
import com.hust.entity.id.AttendId;
import com.hust.exception.ItemNotFoundException;
import com.hust.model.AttendInputModel;
import com.hust.model.StatisticalOutput;
import com.hust.repo.ActivityRepo;
import com.hust.repo.AttendRepo;
import com.hust.repo.CustomRepo;
import com.hust.repo.VolunteerRepo;
import com.hust.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendServiceImpl implements AttendService {
    @Autowired
    AttendRepo attendRepo;
    @Autowired
    VolunteerRepo volunteerRepo;
    @Autowired
    ActivityRepo activityRepo;
    @Autowired
    CustomRepo customRepo;

    @Override
    public List<AttendEntity> findAll() {
        return attendRepo.findAll();
    }

    @Override
    public AttendEntity save(AttendInputModel input, long id) {
        AttendEntity attend = attendRepo.findByActivityIdAndVolunteerId(input.getActivityId(), id);
        if(attend != null){
            attend.setSkill(input.getSkill());
            attend.setState(input.getState());
            attendRepo.save(attend);
        } else {
            attend = new AttendEntity();
            attend.setVolunteer(volunteerRepo.findByUserId(id));
            attend.setActivity(activityRepo.findById(input.getActivityId()));
            attend.setSkill(input.getSkill());
            attend.setState(input.getState());
            attend = attendRepo.save(attend);
        }
        return attend;
    }

    @Override
    public void deleteAttend(AttendId id) {
        attendRepo.delete(attendRepo.findByActivityIdAndVolunteerId(id.getActivity(), id.getVolunteer()));
    }

    @Override
    public List<AttendEntity> findByActivity(long id) {
        return attendRepo.findByActivityId(id);
    }

    @Override
    public List<AttendEntity> findByVolunteer(long id) {
        return attendRepo.findByVolunteerId(id);
    }

    @Override
    public List<StatisticalOutput> getStatistic() {
        return customRepo.getCountAttend();
    }

    @Override
    public int changeState(AttendId id, int state) throws ItemNotFoundException {
        AttendEntity attend = attendRepo.findByActivityIdAndVolunteerId(id.getActivity(), id.getVolunteer());
        if(attend == null) throw new ItemNotFoundException();
        attend.setState(state);
        attendRepo.save(attend);
        return state;
    }
}
