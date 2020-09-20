package com.raketlabs.dao;

import java.util.List;
import java.util.Optional;

import com.raketlabs.model.User;
import com.raketlabs.model.UserRole;


public interface LoginDao {

	Optional<User> findUserInfo(String username);

	List<UserRole> getUserRoles(String username);
}