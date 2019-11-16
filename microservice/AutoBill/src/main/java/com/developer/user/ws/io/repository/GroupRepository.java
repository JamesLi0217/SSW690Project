package com.developer.user.ws.io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.user.ws.io.entity.GroupEntity;
import com.developer.user.ws.io.entity.UserEntity;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
	List<GroupEntity> findAllByUserId(int userId);
	GroupEntity findByGroupId(int id);
	
	
	

}
