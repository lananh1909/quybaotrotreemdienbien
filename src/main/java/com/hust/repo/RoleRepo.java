package com.hust.repo;

import com.hust.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    RoleEntity findOneById(long id);
    RoleEntity save(RoleEntity role);
    RoleEntity findByRoleName(String roleName);
}
