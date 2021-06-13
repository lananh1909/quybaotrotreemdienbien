package com.hust.address.service;

import java.util.List;

import com.hust.address.entity.Province;
import com.hust.address.repo.ProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	private ProvinceRepo provinceRepo;
	
	@Override
	public List<Province> getAllProvince() {
		List<Province> tmp = provinceRepo.findAll();
		return tmp;
	}

}
