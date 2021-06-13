package com.hust.service;

import com.hust.entity.FileDB;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface FileDBService {
    FileDB store(MultipartFile file) throws IOException;
    FileDB getFile(long id);
    List<FileDB> getAllFiles();
    void deleteFile(long id);
}
