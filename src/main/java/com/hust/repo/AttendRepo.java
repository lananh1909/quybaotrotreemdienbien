package com.hust.repo;

import com.hust.entity.ActivityEntity;
import com.hust.entity.AttendEntity;
import com.hust.entity.id.AttendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AttendRepo extends JpaRepository<AttendEntity, AttendId> {
    List<AttendEntity> findAll();
    AttendEntity save(AttendEntity attend);
    List<AttendEntity> findByActivityId(long id);
    List<AttendEntity> findByVolunteerId(long id);
    long countByVolunteerId(long id);
    AttendEntity findByActivityIdAndVolunteerId(long actId, long volId);
}
