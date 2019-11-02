package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.model.User;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	 @Autowired
		private UserDaoImpl userdao;
	 
	 @GetMapping()
	 public String toTest() {
	  return "Welcome to Java Inspires...";
	 }
	 
	 @GetMapping(path = "/createuser")
	 private void createUser() 
	 {
		User user = new User();
		user.setUserEmail("tc1@gmai.com");
		user.setPassword("55553");
		user.setUserName("tc");
		userdao.createUser(user);
	 }
		 
}
