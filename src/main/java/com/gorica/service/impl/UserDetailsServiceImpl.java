package com.gorica.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorica.dao.UserDetailsDao;
import com.gorica.dao.model.Role;
import com.gorica.dao.model.User;
import com.gorica.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsDao userDao;

	@Override
	@Transactional
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public List<Role> getAllRoles() {
		return userDao.getAllRoles();
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public List<User> getActiveUsers() {
		return userDao.getActiveUsers();
	}

	@Override
	public List<User> getInActiveUsers() {
		return userDao.getInActiveUsers();
	}

	@Override
	@Transactional
	public void activateUserById(Long id) {
		userDao.activateUserById(id);
	}

	@Override
	@Transactional
	public void deActivateUserById(Long id) {
		userDao.deActivateUserById(id);
	}

	@Override
	@Transactional
	public void deleteUserById(Long id) {
		userDao.deleteById(id);
	}

	@Override
	public User getSingleUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

}
