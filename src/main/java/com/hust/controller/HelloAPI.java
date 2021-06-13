package com.hust.controller;

import com.hust.entity.RoleEntity;
import com.hust.entity.UserEntity;
import com.hust.model.*;
import com.hust.model.securitymodel.JwtResponse;
import com.hust.model.securitymodel.LoginRequest;
import com.hust.model.securitymodel.MessageResponse;
import com.hust.repo.RoleRepo;
import com.hust.repo.UserRepo;
import com.hust.security.jwt.JwtUtils;
import com.hust.security.services.UserDetailsImpl;
import com.hust.service.UserService;
import com.hust.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class HelloAPI {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;
    
    @Autowired
    VolunteerService volunteerService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        VolunteerOutPutModel vol = volunteerService.findById(userDetails.getId());
        boolean firstLogin = true;
        if(vol != null){
            firstLogin = false;
        }
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles, firstLogin));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AddUserInputModel signupRequest){
        if(userRepo.existsByUsername(signupRequest.getUsername().toLowerCase())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if(userRepo.existsByEmail(signupRequest.getEmail().toLowerCase())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        if(signupRequest.getRoleId() == null){
            RoleEntity userRole = roleRepo.findByRoleName("ROLE_USER");
            signupRequest.setRoleId(userRole.getId());
        }
        signupRequest.setPassword(encoder.encode(signupRequest.getPassword()));
        UserEntity user = userService.save(signupRequest);
        return ResponseEntity.ok().body(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/statistic")
    public ResponseEntity<?> getStatistical(){
        return ResponseEntity.ok().body(userService.getStatistical());
    }
}
