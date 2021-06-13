package com.hust.repo;

import com.hust.entity.ActivityEntity;
import com.hust.entity.FileDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface ActivityRepo extends JpaRepository<ActivityEntity, Long> {
    ActivityEntity save(ActivityEntity act);
    ActivityEntity findById(long id);
    List<ActivityEntity> findByImage(FileDB image);
    Page<ActivityEntity> findByTitleLikeIgnoreCase(String title, Pageable pageable);
    Page<ActivityEntity> findByTitleLikeIgnoreCaseAndTopicId(String title, long topic, Pageable pageable);
    Page<ActivityEntity> findByCommune_District_DistrictId(String districtId, Pageable pageable);
    Page<ActivityEntity> findByTitleLikeIgnoreCaseAndCommune_District_DistrictId(String title, String districtId, Pageable pageable);
    Page<ActivityEntity> findByTopicId(long topic, Pageable pageable);
    long countByTopicId(long id);


    @Query("select a from ActivityEntity a where DATE(fromDate) >= DATE(:date) order by fromDate")
    List<ActivityEntity> findByFromDateAfter(@Param(value = "date") Date date);
}
