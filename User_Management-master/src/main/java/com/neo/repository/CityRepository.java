package com.yash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.entity.CityMaster;

public interface CityRepository extends JpaRepository<CityMaster, Integer> {

	// Retrieve list of cities based on stateId
	// select * from city_master where state_id=?
	public List<CityMaster> findByStateId(int stateId);

}
