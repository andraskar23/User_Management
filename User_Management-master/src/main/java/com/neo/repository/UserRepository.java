package com.yash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	//select * from user_master where email=?
	public User findByEmail(String email);
	
	//select * from user_master where email=? and user_pwd=?
	public User findByEmailAndUserPwd(String email, String pwd);
}
