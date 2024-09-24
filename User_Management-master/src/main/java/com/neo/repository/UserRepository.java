package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	//select * from user_master where email=?
	public User findByEmail(String email);
	
	//select * from user_master where email=? and user_pwd=?
	public User findByEmailAndUserPwd(String email, String pwd);
}
