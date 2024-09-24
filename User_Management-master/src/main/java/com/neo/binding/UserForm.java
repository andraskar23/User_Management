package com.yash.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserForm {
	
	private String fname;
	private String lname;
	private String email;
	private Long phno;
	private LocalDate dob;
	private String gender;
	private int countryId;
	private int stateId;
	private int cityId;

}
