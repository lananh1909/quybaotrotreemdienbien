package com.hust.address.repo;

import com.hust.address.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepo extends JpaRepository<District, String> {
}
