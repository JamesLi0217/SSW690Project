package com.developer.user.ws.io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.user.ws.io.entity.GroupEntity;
import com.developer.user.ws.io.entity.UserEntity;
import java.sql.PreparedStatement;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
//	@Query(value="select * from Users u where u.first_name = ?1",nativeQuery=true)
//	List<UserEntity> findUserByFirstName(String firstName);
	
//	@Query(value="select * from Users u where u.last_name = :lastName",nativeQuery=true)
//	List<UserEntity> findUserByLastName(@Param("lastName") String lastName);
	
	@Query(value="select * from group_user_list g where g.user_id = ?1",nativeQuery=true)
	List<GroupEntity> findAllGroupByUserId(@Param("userId")int userId);
	
	
	List<GroupEntity> findAllByUserId(int userId);
	
//	List<GroupEntity> findByUserId(int userId);
	GroupEntity findByGroupId(int id);
	
	
	

}
