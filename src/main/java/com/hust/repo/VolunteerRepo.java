package com.hust.repo;

import com.hust.entity.UserEntity;
import com.hust.entity.VolunteerEntity;
import com.hust.entity.id.VolunteerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface VolunteerRepo extends JpaRepository<VolunteerEntity, Long> {
    public List<VolunteerEntity> findAll();
    public VolunteerEntity save(VolunteerEntity volunteer);
    VolunteerEntity findByUserId(long id);
    void deleteByUserId(long id);
    VolunteerEntity findByUser(UserEntity user);
}
