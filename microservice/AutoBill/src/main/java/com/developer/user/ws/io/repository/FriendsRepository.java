package com.developer.user.ws.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.developer.user.ws.io.entity.FriendsEntity;
import com.developer.user.ws.io.entity.UserEntity;

@Repository
public interface FriendsRepository extends CrudRepository<FriendsEntity, Long> {
	List<FriendsEntity> findAllByUserId(int userId);
	
	FriendsEntity findByUserId(int userId);

	FriendsEntity save(UserEntity friendEntity);
}
