package com.hust.address.repo;

import com.hust.address.entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuneRepo extends JpaRepository<Commune, String> {
	Commune findByCommuneId(String communeId);
}
