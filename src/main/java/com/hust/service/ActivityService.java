package com.hust.service;

import com.hust.entity.ActivityEntity;
import com.hust.entity.VolunteerEntity;
import com.hust.model.ActivityInputModel;
import com.hust.model.ActivityOutputModel;
import com.hust.model.ActivityPaging;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ActivityService {
    ActivityEntity save(ActivityInputModel input);
    ActivityEntity save(ActivityInputModel input, long id);
    void delete(long id);
    List<VolunteerEntity> getVolunteers(long id);
    ActivityOutputModel getActiviy(long id);
    ActivityPaging findAll(long topic, String districtId, Pageable pageable);

    ActivityPaging findAllByTitle(String title, long topic, String districtId, Pageable pageable);
    List<ActivityOutputModel> getFollowing();
}
