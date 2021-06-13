package com.hust.controller;

import com.hust.entity.UserEntity;
import com.hust.entity.VolunteerEntity;
import com.hust.exception.UserNotFoundException;
import com.hust.model.VolunteerInputModel;
import com.hust.model.VolunteerOutPutModel;
import com.hust.repo.UserRepo;
import com.hust.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/volunteer")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VolunteerAPIController {
    @Autowired
    public VolunteerService volunteerService;

    @Autowired
    public UserRepo userRepo;

    @GetMapping("/get")
    public ResponseEntity<?> getAllVolunteer(Principal principal){
        List<VolunteerOutPutModel> volunteerEntities = volunteerService.findAll();
        return ResponseEntity.ok().body(volunteerEntities);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<?> createVolunteer(Principal principal, @RequestBody VolunteerInputModel input){
        String username = principal.getName();
        VolunteerEntity volunteer = volunteerService.save(input, username);
        return ResponseEntity.ok().body(volunteer);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getVolunteer(Principal principal){
        String username = principal.getName();
        UserEntity user = userRepo.findByUsername(username);
        if(user == null) try {
            throw new UserNotFoundException();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found!");
        }
        VolunteerOutPutModel volunteer = volunteerService.findById(user.getId());
        return ResponseEntity.ok().body(volunteer);
    }
}
