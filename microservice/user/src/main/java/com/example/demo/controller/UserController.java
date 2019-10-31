package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.model.User;

import antlr.collections.List;

@RestController
public class UserController {

	@Autowired
	private UserDaoImpl userdao;
//	 @Autowired private DataSource dataSource;

	@RequestMapping(path = "/home")
	@GetMapping()
	public String toTest() {
		return "Welcome to Java Inspires...";
	}

	@RequestMapping(path = "/")
	public String defaultPage() {
		return "Welcome to default page ...";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String toTestLogin() {
		return "Sucess login...";
	}

	@RequestMapping(path = "/loginerror")
	public String toTestLoginError() {
		return "fail to login...";
	}

	@RequestMapping(path = "/register/{name}/{password}/{email}", method = RequestMethod.GET)
	private void register(@PathVariable("name") String name, @PathVariable("password") String password,
			@PathVariable("email") String email) {
		User user = new User();

		user.setUserEmail(email);
		user.setPassword(password);
		user.setUserName(name);
		userdao.createUser(user);
	}

	// for search name by id
	@RequestMapping(path = "/getUserName/{id}")
	private String getUserName(@PathVariable("id") String id) {
		int id_num = Integer.parseInt(id);
		return userdao.getUserName(id_num);
	}

	// for search email by id
	@RequestMapping(path = "/getUserEmail/{id}")
	private String getUserEmail(@PathVariable("id") String id) {
		int id_num = Integer.parseInt(id);
		return userdao.getUserEmail(id_num);
	}

	// for search id by name
	@RequestMapping(path = "/getUserID/{name}")
	private Integer getUserID(@PathVariable("name") String name) {
		return userdao.getUserID(name);
	}

	// for search someone's friends by id
	@RequestMapping(path = "/findAllFriends/{id}")
	private ArrayList<Integer> findAllFriends(@PathVariable("id") String id) {
		int id_num = Integer.parseInt(id);
		return userdao.findAllFriends(id_num);
	}
	
	// want to add friend with someone
	@RequestMapping(path = "/addFriend/{id1}/{id2}")
	private boolean addFriend(@PathVariable("id1") String id1, @PathVariable("id2") String id2) {
		int id_num1 = Integer.parseInt(id1);
		int id_num2 = Integer.parseInt(id2);
		return userdao.addFriend(id_num1, id_num2);
	}
	
	// confirm friend with someone
	@RequestMapping(path = "/confirmFriend/{id1}/{id2}")
	private boolean confirmFriend(@PathVariable("id1") String id1, @PathVariable("id2") String id2) {
		int id_num1 = Integer.parseInt(id1);
		int id_num2 = Integer.parseInt(id2);
		if (userdao.needConfirmFriend(id_num1, id_num2)) {
			return userdao.addFriend(id_num1, id_num2);
		}
		else {
			return false;  // no such confirm
		}
	}
	
	@RequestMapping(path = "/denyFriend/{id1}/{id2}")
	private boolean denyFriend(@PathVariable("id1") String id1, @PathVariable("id2") String id2) {
		int id_num1 = Integer.parseInt(id1);
		int id_num2 = Integer.parseInt(id2);
		if (userdao.needConfirmFriend(id_num1, id_num2)) {
			return userdao.denyFriend(id_num1, id_num2);
		}
		return false;  // no such deny
	}
}
