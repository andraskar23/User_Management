package com.yash.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="COUNTRY_MASTER")
@Data
public class CountryMaster {

	@Id
	private int countryId;
	private String countryName;
}
