package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	 @Autowired
	 UserRepository userRepository;
	 
	 @Autowired
		private UserDaoImpl userdao;
	 
	 @GetMapping
	 public String toTest() {
	  return "Welcome to Java Inspires...";
	 }
	
	 
	 @GetMapping(path = "/getusernames")
	 public List<String> getAllUserNames() {
	  return userRepository.getAllUserNames();
	 }
	 
	 @GetMapping(path = "/createuser")
	 private void createUser() 
	 {
		User user = new User();
		user.setUserEmail("xinq1@gmai.com");
		user.setPassword("123");
		user.setUserName("qxh");
		userdao.createUser(user);
	 }
		 
}
