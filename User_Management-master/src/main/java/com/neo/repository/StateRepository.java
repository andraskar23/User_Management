package com.neo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.entity.StateMaster;

public interface StateRepository extends JpaRepository<StateMaster, Integer> {
	
	//Retrieve list of states based on countryId
	//select * from state_master where country_id=?
	public List<StateMaster> findByCountryId(int countryId);

}
