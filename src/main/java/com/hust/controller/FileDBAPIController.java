package com.hust.controller;

import com.hust.entity.FileDB;
import com.hust.model.ResponseFileModel;
import com.hust.service.FileDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/file")
public class FileDBAPIController {
    @Autowired
    FileDBService fileDBService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String type = file.getOriginalFilename().split("\\.")[1];
            if(type.equals("jpg") || type.equals("png") || type.equals("jpeg") || type.equals("gif"))
                return ResponseEntity.ok().body(fileDBService.store(file));
            else
                return ResponseEntity.badRequest().body("File is not image");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload file: " + file.getOriginalFilename());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getListFiles() {
        List<ResponseFileModel> files = new ArrayList<>();
        List<FileDB> list = fileDBService.getAllFiles();
        for (FileDB f : list) {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(f.getId()))
                    .toUriString();
            files.add(new ResponseFileModel(f.getName(), fileDownloadUri, f.getType(), f.getData().length));
        }
        return ResponseEntity.ok().body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable("id") long id){
        FileDB fileDB = fileDBService.getFile(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id){
        fileDBService.deleteFile(id);
    }
}
