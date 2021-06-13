package com.hust.address.service;

import java.util.ArrayList;
import java.util.List;

import com.hust.address.entity.District;
import com.hust.address.repo.DistrictRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {
	@Autowired
	private DistrictRepo districtRepo;
	
	@Override	
	public List<District> getAllDistrict(String provinceId) {
		// TODO Auto-generated method stub
		List<District> tmp = districtRepo.findAll();
		List<District> dis = new ArrayList<District>();
		for(District a: tmp) {
			if(a.getProvince().getProvinceId().equals(provinceId))
				dis.add(a);
		}
		return dis;
	}

}
