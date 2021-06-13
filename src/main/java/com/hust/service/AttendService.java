package com.hust.service;

import com.hust.entity.AttendEntity;
import com.hust.entity.id.AttendId;
import com.hust.exception.ItemNotFoundException;
import com.hust.model.AttendInputModel;
import com.hust.model.StatisticalOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendService {
    List<AttendEntity> findAll();
    AttendEntity save(AttendInputModel input, long id);
    void deleteAttend(AttendId id);
    List<AttendEntity> findByActivity(long id);
    List<AttendEntity> findByVolunteer(long id);
    List<StatisticalOutput> getStatistic();
    int changeState(AttendId id, int state) throws ItemNotFoundException;
}
