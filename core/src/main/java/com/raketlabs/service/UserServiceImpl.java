package com.raketlabs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raketlabs.model.User;
import com.raketlabs.model.UserRole;
import com.raketlabs.repository.UserInfoRepository;
import com.raketlabs.repository.UserRoleRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserInfoRepository userRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> list() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findUserByUsername(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public void update(String userName, String password) {
		Optional<User> user = userRepository.findByUserName(userName);
		
		if (user.isPresent()) {
			user.get().setPassword(passwordEncoder.encode(password));
			userRepository.save(user.get());
		} else {
			throw new UsernameNotFoundException("Can't update password. Username was not found.");
		}
	}

	@Override
	public void add(String userName, String password) {
		
		UserRole userRole = new UserRole();
		userRole.setUserName(userName);
		userRole.setRole("ROLE_USER");
		
		User newUser = new User();
		newUser.setUserName(userName);
		newUser.setPassword(passwordEncoder.encode(password));
		
		Optional<User> userInfo = userRepository.findByUserName(userName);
		
		
		if (userInfo.isPresent()) {
			throw new IllegalArgumentException("Can't sign up. User already exists.");
		} else {
			userRepository.save(newUser);
			userRoleRepository.save(userRole);
		}
	}

	@Override
	public boolean userExists(String userName) {
		return userRepository.existsByUserName(userName);
	}

	@Override
	public List<UserRole> getUserRoles(String userName) {
		userRoleRepository.findByUserName(userName);
		return null;
	}
	
	

}
