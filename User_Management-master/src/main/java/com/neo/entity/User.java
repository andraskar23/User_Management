package com.neo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="USER_MASTER")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String fname;
	private String lname;
	private String email;
	private Long phno;
	private LocalDate dob;
	private String gender;
	private int countryId;
	private int stateId;
	private int cityId;
	private String userPwd;
	private String accStatus;
}
