package com.candidjava.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.candidjava.spring.bean.User;
import com.google.common.base.Optional;
public interface UserRepository extends CrudRepository<User, Long>
{
	
	Optional<User> findByUserNameAndPassword(String userName, String password);

}
