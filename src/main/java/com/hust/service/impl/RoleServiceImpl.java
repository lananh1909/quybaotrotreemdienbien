package com.hust.service.impl;

import com.hust.entity.RoleEntity;
import com.hust.repo.RoleRepo;
import com.hust.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public RoleEntity save(String roleName) {
        RoleEntity role = new RoleEntity();
        role.setRoleName(roleName);
        role = roleRepo.save(role);
        return role;
    }

    @Override
    public List<RoleEntity> findAll() {
        List<RoleEntity> roles = roleRepo.findAll();
        return roles;
    }
}
