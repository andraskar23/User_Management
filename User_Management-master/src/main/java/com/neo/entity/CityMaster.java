package com.yash.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "CITY_MASTER")
@Data
public class CityMaster {
	
	@Id
	private int cityId;
	private String cityName;
	private int stateId;

}
