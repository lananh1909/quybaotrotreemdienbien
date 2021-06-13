package com.hust.address.service;

import java.util.List;

import com.hust.address.entity.District;
import org.springframework.stereotype.Service;
@Service
public interface DistrictService {
	public List<District> getAllDistrict(String provinceId);
}
