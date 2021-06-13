package com.hust.address.service;

import java.util.ArrayList;
import java.util.List;

import com.hust.address.entity.Commune;
import com.hust.address.repo.CommuneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommuneServiceImpl implements CommuneService {

	@Autowired
	private CommuneRepo communeRepo;
	
	@Override
	public List<Commune> getAllCommune(String districtId) {
		// TODO Auto-generated method stub
		List<Commune> com = communeRepo.findAll();
		List<Commune> tmp = new ArrayList<Commune>();
		for(Commune a: com) {
			if(a.getDistrict().getDistrictId().equals(districtId))
				tmp.add(a);
		}
		return tmp;
	}
	
	@Override
	public Commune findByCommuneId(String communeId) {
		return communeRepo.findByCommuneId(communeId);
	}

}
