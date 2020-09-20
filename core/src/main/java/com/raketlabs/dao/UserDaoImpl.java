package com.raketlabs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raketlabs.model.User;
import com.raketlabs.repository.UserInfoRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Override
	public List<User> list() {
		return userInfoRepository.findAll();
	}

	@Override
	public User findUserByUsername(String userName) {
		return userInfoRepository.findByUserName(userName).get();
	}

	@Override
	public void update(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		userInfoRepository.save(user);
	}

	@Override
	public void add(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		userInfoRepository.save(user);
	}

	@Override
	public boolean userExists(String userName) {
		return userInfoRepository.existsByUserName(userName);
	}

}
