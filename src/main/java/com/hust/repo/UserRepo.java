package com.hust.repo;

import com.hust.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity save(UserEntity user);

    UserEntity findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    UserEntity findById(long id);

    Page<UserEntity> findAll(Pageable pageable);

    Page<UserEntity> findByUsernameLikeIgnoreCase(String username, Pageable pageable);

    Page<UserEntity> findByRoleId(long role_id, Pageable pageable);

    Page<UserEntity> findByUsernameLikeIgnoreCaseAndRoleId(String username, long role_id, Pageable pageable);
}
