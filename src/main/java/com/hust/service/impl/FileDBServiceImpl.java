package com.hust.service.impl;

import com.hust.entity.FileDB;
import com.hust.repo.FileDBRepo;
import com.hust.service.FileDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileDBServiceImpl implements FileDBService {
    @Autowired
    FileDBRepo fileDBRepo;

    @Override
    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB f1 = fileDBRepo.findOneByName(fileName);
        if(f1 != null){
            return f1;
        }
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepo.save(fileDB);
    }

    @Override
    public FileDB getFile(long id) {
        return fileDBRepo.findById(id);
    }

    @Override
    public List<FileDB> getAllFiles() {
        return fileDBRepo.findAll();
    }

    @Override
    public void deleteFile(long id) {
        fileDBRepo.deleteById(id);
    }
}
