package com.hust.controller;


import com.hust.entity.TopicEntity;
import com.hust.repo.TopicRepo;
import com.hust.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/topic")
public class TopicAPIController {
    @Autowired
    TopicService topicService;

    @Autowired
    TopicRepo topicRepo;

    @PostMapping("/create")
    public ResponseEntity<?> createTopic(Principal principal, @RequestBody String name){
        if(topicRepo.existsByTopicName(name)){
            return ResponseEntity.badRequest().body("Chủ đề đã tồn tại");
        }
        return ResponseEntity.ok().body(topicService.save(name));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllTopic(Principal principal){
        return ResponseEntity.ok().body(topicService.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTopic(@RequestBody String name, @PathVariable("id") long id){
        if(topicRepo.existsByTopicNameAndIdNot(name, id)){
            return  ResponseEntity.badRequest().body("Chủ đề đã tồn tại");
        }
        return ResponseEntity.ok().body(topicService.save(name, id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTopic(@RequestBody long id){
        try{
            topicService.delete(id);
            return ResponseEntity.ok().body("SUCCESS");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Không thể xóa chủ đề này!");
        }
    }
}
