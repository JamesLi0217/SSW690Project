package com.developer.user.ws.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.developer.user.ws.io.entity.GroupEntity;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
	
	GroupEntity findByUserId(int id);

}
