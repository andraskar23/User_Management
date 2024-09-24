package com.yash.service;

import java.util.Map;

import com.yash.binding.LoginForm;
import com.yash.binding.UnlockAccountForm;
import com.yash.binding.UserForm;

public interface UserMgmtService {

	public String checkEmail(String email);

	public Map<Integer, String> getCountries();

	public Map<Integer, String> getStates(int countryId);

	public Map<Integer, String> getCities(int stateId);

	public String registerUser(UserForm user);

	public String unlockAccount(UnlockAccountForm accForm);

	public String login(LoginForm loginForm);

	public String forgotPwd(String email);
}
