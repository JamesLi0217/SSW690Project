package com.developer.user.ws.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.developer.user.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {


	UserEntity findByUserEmail(String email);
	UserEntity findByUserId(int id);

}
