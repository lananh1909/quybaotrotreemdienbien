package com.hust.address.service;

import java.util.List;

import com.hust.address.entity.Commune;
import org.springframework.stereotype.Service;

@Service
public interface CommuneService {
	public List<Commune> getAllCommune(String districtId);
	
	public Commune findByCommuneId(String communeId);
}
