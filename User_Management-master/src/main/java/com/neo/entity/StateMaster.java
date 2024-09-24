package com.neo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "STATE_MASTER")
@Data
public class StateMaster {

	@Id
	private int stateId;
	private String stateName;
	private int countryId;
}
