package com.hust.service;

import com.hust.entity.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    RoleEntity save(String roleName);
    List<RoleEntity> findAll();
}
