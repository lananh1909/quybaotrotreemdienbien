package com.hust.address.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hust.entity.UserEntity;
import com.hust.entity.VolunteerEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Commune {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="commune_id")
	private String communeId;
	
	@Column(name="commune_name")
	private String communeName;
	
	@JoinColumn(name = "district_id", referencedColumnName = "district_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private District district;

	@OneToMany(mappedBy = "commune")
	@JsonIgnore
	private List<VolunteerEntity> volunteers = new ArrayList<>();

	@OneToMany(mappedBy = "commune")
	@JsonIgnore
	private List<VolunteerEntity> activities = new ArrayList<>();

	public List<VolunteerEntity> getActivities() {
		return activities;
	}

	public void setActivities(List<VolunteerEntity> activities) {
		this.activities = activities;
	}

	public String getCommuneId() {
		return communeId;
	}

	public void setCommuneId(String communeId) {
		this.communeId = communeId;
	}

	public String getCommuneName() {
		return communeName;
	}

	public void setCommuneName(String communeName) {
		this.communeName = communeName;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<VolunteerEntity> getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(List<VolunteerEntity> volunteers) {
		this.volunteers = volunteers;
	}
}

