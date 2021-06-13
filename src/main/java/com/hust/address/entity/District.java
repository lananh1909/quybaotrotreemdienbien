package com.hust.address.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class District {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="district_id")
	private String districtId;
	
	@Column(name="district_name")
	private String districtName;
	
	@JoinColumn(name = "province_id", referencedColumnName = "province_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Province province;

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
}

