package com.hust.address.service;

import java.util.List;

import com.hust.address.entity.Province;
import org.springframework.stereotype.Service;

@Service
public interface ProvinceService {
	public List<Province> getAllProvince();
}
