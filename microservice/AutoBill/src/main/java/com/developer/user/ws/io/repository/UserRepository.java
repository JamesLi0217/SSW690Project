package com.developer.user.ws.io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.user.ws.io.entity.GroupEntity;
import com.developer.user.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {


	UserEntity findByUserEmail(String email);
	UserEntity findByUserId(int id);
	
	@Query(value="select * from group_user_list u where u.user_id = :user_id",nativeQuery=true)
	List<GroupEntity> findGroupByUserId(@Param("user_id") int user_id);

//	@Query(value="select * from friend_list u where u.user_id = :user_id",nativeQuery=true)
//	List<UserEntity> findFriendsByUserId(int userId);
}
