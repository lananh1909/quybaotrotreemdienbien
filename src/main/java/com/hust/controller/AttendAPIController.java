package com.hust.controller;

import com.hust.entity.AttendEntity;
import com.hust.entity.UserEntity;
import com.hust.entity.id.AttendId;
import com.hust.exception.ItemNotFoundException;
import com.hust.exception.UserNotFoundException;
import com.hust.model.AttendInputModel;
import com.hust.model.ChangeStateInputModel;
import com.hust.repo.UserRepo;
import com.hust.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/attend")
public class AttendAPIController {
    @Autowired
    AttendService attendService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    JavaMailSender mailSender;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/insert")
    public ResponseEntity<?> insertAttend(Principal principal, @RequestBody AttendInputModel input){
        UserEntity user = userRepo.findByUsername((principal.getName()));
        if(user == null) try {
            throw new UserNotFoundException();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("User not found!");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Xác nhận đăng ký hoạt động");
        AttendEntity attendEntity = attendService.save(input, user.getId());
        String content = "Cảm ơn bạn đã đăng ký tham gia hoạt động \"" + attendEntity.getActivity().getTitle() + "\"";
        message.setText(content);
        this.mailSender.send(message);
        return ResponseEntity.ok().body(attendEntity);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAllAttend(Principal principal, @PathVariable("id") long id){
        return ResponseEntity.ok().body(attendService.findByActivity(id));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/activities")
    public ResponseEntity<?> getByVolunteer(Principal principal){
        UserEntity user = userRepo.findByUsername((principal.getName()));
        if(user == null) try {
            throw new UserNotFoundException();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("User not found!");
        }
        return ResponseEntity.ok().body(attendService.findByVolunteer(user.getId()));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public void deleteAttend(Authentication authentication, @RequestBody AttendId input){
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))){
            UserEntity user = userRepo.findByUsername(authentication.getName());
            if(user.getId() != input.getVolunteer())
                return;
        }
        attendService.deleteAttend(input);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/change-state")
    public ResponseEntity<?> changeState(@RequestBody ChangeStateInputModel input){
        try{
            int state = attendService.changeState(new AttendId(input.getActId(), input.getVolId()), input.getState());
            return ResponseEntity.ok().body(state);
        } catch (ItemNotFoundException ex){
            return ResponseEntity.badRequest().body("Item not found");
        }
    }
}
