package com.hust.repo;

import com.hust.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FileDBRepo extends JpaRepository<FileDB, Long> {
    FileDB save(FileDB file);
    List<FileDB> findAll();
    FileDB findById(long id);
    FileDB findOneByName(String name);
}
