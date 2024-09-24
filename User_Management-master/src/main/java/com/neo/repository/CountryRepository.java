package com.yash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.entity.CountryMaster;

public interface CountryRepository extends JpaRepository<CountryMaster, Integer> {

}
