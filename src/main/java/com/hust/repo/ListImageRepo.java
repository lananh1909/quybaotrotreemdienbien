package com.hust.repo;

import com.hust.entity.ListImage;
import com.hust.entity.id.ListImageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ListImageRepo extends JpaRepository<ListImage, ListImageId> {
    List<ListImage> findAll();
    ListImage save(ListImage image);
    List<ListImage> findByBlogId(long id);
    List<ListImage> findByImageId(long id);
}
