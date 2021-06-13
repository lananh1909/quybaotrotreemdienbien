package com.hust.address.repo;

import com.hust.address.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepo extends JpaRepository<Province, String> {
}
