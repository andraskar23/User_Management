package com.yash.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.binding.LoginForm;
import com.yash.binding.UnlockAccountForm;
import com.yash.binding.UserForm;
import com.yash.service.UserMgmtService;

@RestController
public class UserRestController {

	@Autowired
	private UserMgmtService userMgmtService;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginForm loginForm){
		String status = userMgmtService.login(loginForm);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@GetMapping("/countries")
	public Map<Integer,String> loadCountries(){
		return userMgmtService.getCountries();
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer,String> loadStates(@PathVariable int countryId){
		return userMgmtService.getStates(countryId);
	}
	
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer,String> loadCities(@PathVariable int stateId){
		return userMgmtService.getCities(stateId);
	}
	
	@GetMapping("/email/{email}")
	public String emailCheck(@PathVariable String email) {
		return userMgmtService.checkEmail(email);
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> userRegistration(@RequestBody UserForm userForm){
		String status = userMgmtService.registerUser(userForm);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	@PostMapping("/unlock")
	public ResponseEntity<String> unlockAccount(@RequestBody UnlockAccountForm UnlockAccForm){
		String status = userMgmtService.unlockAccount(UnlockAccForm);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@GetMapping("/forgotpwd")
	public ResponseEntity<String> forgotPwd(@PathVariable String email){
		String status = userMgmtService.forgotPwd(email);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
}
