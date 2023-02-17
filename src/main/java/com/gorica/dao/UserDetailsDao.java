package com.gorica.dao;

import java.util.List;

import com.gorica.dao.model.Role;
import com.gorica.dao.model.User;
import com.gorica.dao.repository.BaseRepositoryI;

public interface UserDetailsDao extends BaseRepositoryI<User, Long> {

	public User getUserByUserName(String userName);

	public List<Role> getAllRoles();

	public List<User> getActiveUsers();

	public List<User> getInActiveUsers();
	
	public void activateUserById(Long id);

	public void deActivateUserById(Long id);

}
