package com.hust.repo;

import com.hust.entity.BlogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BlogRepo extends JpaRepository<BlogEntity, Long> {
    BlogEntity findOneById(long id);
    List<BlogEntity> findAll();
    BlogEntity save(BlogEntity blog);
    Page<BlogEntity> findByTitleLikeIgnoreCase(String title, Pageable pageable);
}
