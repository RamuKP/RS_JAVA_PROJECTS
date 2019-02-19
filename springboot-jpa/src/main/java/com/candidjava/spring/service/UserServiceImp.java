package com.candidjava.spring.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.spring.bean.User;
import com.candidjava.spring.repository.UserRepository;
import com.google.common.base.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository userRepository;
	EntityManager entityManager;
	

	public void createUser(User user) {
		userRepository.save(user);
	}

	public List<User> getUser() {
		return (List<User>) userRepository.findAll();
	}

	public User findById(long id) {
		return userRepository.findOne(id);
	}

	public User update(User user, long l) {
		return userRepository.save(user);
	}

	public void deleteUserById(long id) {
		userRepository.delete(id);
	}

	public User updatePartially(User user, long id) {
		User usr = findById(id);
		usr.setPassword(user.getPassword());
		return userRepository.save(usr);
	}

	@Override
	public boolean login(String userName, String password) {
		Optional<User> userFEROptional = userRepository.findByUserNameAndPassword(userName, password);

		return userFEROptional != null && userFEROptional.isPresent();
	}
	
	@Override
	@Transactional
	public boolean resetPassword(Long id, String oldPassword, String newPassword) {
		try {
			String hql = "update User u set u.password='"+newPassword+"' where u.id='"+id+"' and u.password='"+oldPassword+"'";
			Query query = entityManager.createQuery(hql);
			query.setParameter(1, newPassword);
			query.setParameter(2, id);
			query.setParameter(3, oldPassword);

			return query.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}

}
