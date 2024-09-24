package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.entity.CountryMaster;

public interface CountryRepository extends JpaRepository<CountryMaster, Integer> {

}
