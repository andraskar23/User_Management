package com.neo.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.binding.LoginForm;
import com.neo.binding.UnlockAccountForm;
import com.neo.binding.UserForm;
import com.neo.entity.CityMaster;
import com.neo.entity.CountryMaster;
import com.neo.entity.StateMaster;
import com.neo.entity.User;
import com.neo.repository.CityRepository;
import com.neo.repository.CountryRepository;
import com.neo.repository.StateRepository;
import com.neo.repository.UserRepository;
import com.neo.utils.EmailUtils;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public String checkEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(email);
		
		if(user == null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	}

	@Override
	public Map<Integer, String> getCountries() {
		// TODO Auto-generated method stub
		List<CountryMaster> counties = countryRepo.findAll();
		
		Map<Integer, String> countryMap = new HashMap<>();
		counties.forEach(country -> {
			countryMap.put(country.getCountryId(), country.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(int countryId) {
		// TODO Auto-generated method stub
		List<StateMaster> states = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();
		states.forEach(state -> {
			stateMap.put(state.getStateId(), state.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(int stateId) {
		// TODO Auto-generated method stub
		List<CityMaster> cities = cityRepo.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		cities.forEach(city -> {
			cityMap.put(city.getCityId(), city.getCityName());
		});
		return cityMap;
	}

	@Override
	public String registerUser(UserForm userForm) {
		// TODO Auto-generated method stub
		//copy data from binding obj to entity obj
		User entity = new User();
		BeanUtils.copyProperties(userForm, entity);
		
		//Generate & Set random pwd
		entity.setUserPwd(generateRandomPwd());
		
		//Set Account status as LOCKED
		entity.setAccStatus("LOCKED");
		
		userRepo.save(entity);
		
		
		//send email to unlock account
		
		String to = userForm.getEmail();
		String subject = "Registration Email";
		String body = readEmailBody("REG_EMAIL_BODY.txt", entity);
		
		emailUtils.SendEmail(to, subject, body);
		
		
		return "User Account Created";
	}

	@Override
	public String unlockAccount(UnlockAccountForm unlockAccForm) {
		// TODO Auto-generated method stub
		String email = unlockAccForm.getEmail();
		User user = userRepo.findByEmail(email);
		
		if(user.getUserPwd().equals(unlockAccForm.getConfirmPwd())) {
			user.setUserPwd(unlockAccForm.getNewPwd());
			user.setAccStatus("UNLOCKED");
			userRepo.save(user);
		}
		return "INVALID TEMPORARY PASSWORD";
	}

	@Override
	public String login(LoginForm loginForm) {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmailAndUserPwd(loginForm.getEmail(), loginForm.getPwd());
		if(user == null) {
			return "Invalid Credentials";
		}
		if(user.getAccStatus().equals("LOCKED")) {
			return "Account Locked";
		}
		return "SUCCESSs";
	}

	@Override
	public String forgotPwd(String email) {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(email);
		
		if(user==null) {
			return "No Account Found";
		}
		
		//Send email to user with pwd
		String subject = "Recover Password";
		String body = readEmailBody("FORGOT_PWD_EMAIL_BODY.txt", user);
		
		emailUtils.SendEmail(email, subject, body);
		return "Password ";
	}
	
	
	private String generateRandomPwd() {
		String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuilder sb = new StringBuilder("");
		
		Random random = new Random();
		
		int pwdLength =6;
		
		for(int i=1;i<=pwdLength;i++) {
			int index = random.nextInt(text.length());
			sb.append(text.charAt(index));	
		}
		return sb.toString();
	}
	
	private String readEmailBody(String filename, User user) {
		
		StringBuffer sb = new StringBuffer();
		
		try(Stream<String> lines = Files.lines(Paths.get(filename))){
			lines.forEach(line ->{
				line = line.replace("${FNAME}", user.getFname());
				line = line.replace("${LNAME}", user.getLname());
				line = line.replace("${TEMP_PWD}", user.getUserPwd());
				line = line.replace("${EMAIL}", user.getEmail());
				line = line.replace("${PWD}", user.getUserPwd());
				
				
				sb.append(line);
				
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return sb.toString();
		
	}

}
