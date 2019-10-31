package com.example.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;

import antlr.collections.List;

@Repository
//public class UserDaoImpl extends JdbcDaoSupport implements UserDao
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate((javax.sql.DataSource) dataSource);
	}

	@Override
	public void createUser(User user) {
		String CREATE_USER_SQL = "INSERT INTO users(user_name,user_password, user_email,  user_start_date) VALUES (?,?,?,?)";

		int update = jdbcTemplate.update(CREATE_USER_SQL, user.getUserName(), user.getPassword(), user.getUserEmail(),
				1995 - 7 - 11);
		if (update == 1) {

			System.out.println("User is create..");
		}
	}

	@Override
	public User getUser(int user_id) {
		// TODO Auto-generated method stub
		String GET_USER_SQL = "select * from user where user_id=?";
		System.out.println("success");
		return jdbcTemplate.queryForObject(GET_USER_SQL, new UserRowMapper(), user_id);
	}

	class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setPassword(rs.getString("user_password"));
			System.out.println(user.user_name);
			return user;
		}
	}

	@Override
	public String getUserName(int user_id) {
		// TODO Auto-generated method stub
		String GET_USER_NAME_SQL = "select user_name from users where user_id=?";
		System.out.println("get user name success");
		return jdbcTemplate.queryForObject(GET_USER_NAME_SQL, String.class, user_id);
//		return this.getJdbcTemplate().queryForObject(GET_USER_NAME_SQL, String.class, user_id);
//		return null;
	}

	@Override
	public String getUserEmail(int user_id) {
		// TODO Auto-generated method stub
		String GET_USER_Eamil_SQL = "select user_email from users where user_id=?";
		System.out.println("get user email success");
		return jdbcTemplate.queryForObject(GET_USER_Eamil_SQL, String.class, user_id);
	}

	@Override
	public Integer getUserID(String user_name) {
		// TODO Auto-generated method stub
		String GET_USER_ID_SQL = "select user_id from users where user_name=?";
		System.out.println("get user ID success");
		return jdbcTemplate.queryForObject(GET_USER_ID_SQL, Integer.class, user_name);
	}

	@Override
	public ArrayList<Integer> findAllFriends(int user_id) {
		// TODO Auto-generated method stub
		// INSERT INTO `autobill_db`.`friend_list` (`user_id`, `friend_id`,
		// `comfirm_state_id`) VALUES ('1', '2', '1');
		String sql = "select friend_id from friend_list where user_id= ? and comfirm_state_id = 1";
		return (ArrayList<Integer>) jdbcTemplate.queryForList(sql, Integer.class, user_id);
	}

	@Override
	public boolean addFriend(int user1_id, int user2_id) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO autobill_db.friend_list (user_id, friend_id, comfirm_state_id) " + "VALUES (?, ?, ?)";
		int update = jdbcTemplate.update(sql, user1_id, user2_id, 0);
		if (update == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean confirmFriend(int user1_id, int user2_id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE friend_list SET comfirm_state_id = 1 WHERE (user_id = ? and friend_id = ? )";
		int update = jdbcTemplate.update(sql, user1_id, user2_id);
		String sql2 = "INSERT INTO autobill_db.friend_list (user_id, friend_id, comfirm_state_id) " + "VALUES (?, ?, ?)";
		int addEach = jdbcTemplate.update(sql2, user2_id, user1_id, 1); // a is b friend so b is a friend
		if (update == 1) {
			return true;
		}
		return false;
	}
	

	@Override
	public boolean denyFriend(int user1_id, int user2_id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE friend_list SET comfirm_state_id = -1 WHERE (user_id = ? and friend_id = ? )";
		int update = jdbcTemplate.update(sql, user1_id, user2_id);
		if (update == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean needConfirmFriend(int user1_id, int user2_id) {
		// TODO Auto-generated method stub
		String sql = "select comfirm_state_id from friend_list WHERE (user_id= ? having count(friend_id)) = ? ";
//		select comfirm_state_id from autobill_db.friend_list where user_id = 22 having count(friend_id) = 1;

		int update = jdbcTemplate.update(sql, user1_id, user2_id);
		if (update == 1) {
			return true;
		}
		return false;
	}

}
