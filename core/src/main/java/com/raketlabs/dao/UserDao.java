package com.raketlabs.dao;

import java.util.List;

import com.raketlabs.model.User;


public interface UserDao {

	public List<User> list();

	public User findUserByUsername(String username);

	public void update(String username, String password);

	public void add(String username, String password);

	public boolean userExists(String username);

}