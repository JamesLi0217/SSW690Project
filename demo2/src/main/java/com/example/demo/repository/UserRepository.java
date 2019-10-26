package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<String> getAllUserNames(){
		List<String> usernameList = new ArrayList<>();
		usernameList.addAll(jdbctemplate.queryForList("select user_name from users;",String.class));
		return usernameList;
	}
}
