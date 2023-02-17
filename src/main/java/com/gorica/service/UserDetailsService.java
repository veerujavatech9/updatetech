package com.gorica.service;

import java.util.List;

import com.gorica.dao.model.Role;
import com.gorica.dao.model.User;

public interface UserDetailsService {

	public void saveUser(User user);

	public User getUserByUserName(String userName);

	public List<User> getAllUsers();

	public User getUserById(Long id);

	public List<Role> getAllRoles();

	public void updateUser(User user);

	public List<User> getActiveUsers();

	public List<User> getInActiveUsers();

	public void activateUserById(Long id);

	public void deActivateUserById(Long id);
	
	public void deleteUserById(Long id);
	
	public User getSingleUserByUserName(String userName);
}
