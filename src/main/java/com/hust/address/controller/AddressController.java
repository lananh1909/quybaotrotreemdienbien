package com.hust.address.controller;

import java.security.Principal;
import java.util.List;

import com.hust.address.entity.Commune;
import com.hust.address.entity.District;
import com.hust.address.entity.Province;
import com.hust.address.model.InputModelCommune;
import com.hust.address.model.InputModelDistrict;
import com.hust.address.service.CommuneService;
import com.hust.address.service.DistrictService;
import com.hust.address.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private CommuneService communeService;
	
	
	@GetMapping("/provinces")
	public ResponseEntity<?> getAllProvince(){
		List<Province> depts = provinceService.getAllProvince();
		return ResponseEntity.ok().body(depts);
	}
	
	@PostMapping("/districts")
	public ResponseEntity<?> getAllDistrict(Principal principal, @RequestBody InputModelDistrict input){
		List<District> dis = districtService.getAllDistrict(input.getProvinceId());
		return ResponseEntity.ok().body(dis);
	}
	
	@PostMapping("/communes")
	public ResponseEntity<?> getAllCommune(Principal principal, @RequestBody InputModelCommune input){
		List<Commune> com = communeService.getAllCommune(input.getDistrictId());
		return ResponseEntity.ok().body(com);
	}
}
