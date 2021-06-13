package com.hust.controller;

import com.hust.entity.RoleEntity;
import com.hust.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class RoleAPIController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/create-role")
    public ResponseEntity<?> createRole(Principal principal, @RequestBody String input){
        RoleEntity role = roleService.save(input);
        return ResponseEntity.ok().body(role);
    }

    @GetMapping("/get-roles")
    public ResponseEntity<?> getAllRoles(Principal principal){
        List<RoleEntity> roles = roleService.findAll();
        return ResponseEntity.ok().body(roles);
    }

}
