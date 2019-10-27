package com.example.demo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;


@Repository
public class UserDaoImpl implements UserDao {

	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void createUser(User user) {
		String CREATE_USER_SQL = "INSERT INTO users(user_name,user_password, user_email,  user_start_date) VALUES (?,?,?,?)";
		System.out.print("wwwww");
		int update = jdbcTemplate.update(CREATE_USER_SQL, user.getUserName(), user.getPassword(), user.getUserEmail(), 1995-7-8);
		if(update ==1) {
			System.out.println("User is create..");
		}
	}

	@Override
	public User getID(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserName(String user_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getPassword(String user_password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserEmail(String user_email) {
		// TODO Auto-generated method stub
		return null;
	}

}
