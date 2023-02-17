package com.gorica.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gorica.dao.UserDetailsDao;
import com.gorica.dao.model.Role;
import com.gorica.dao.model.User;
import com.gorica.dao.repository.BaseRepositoryImpl;

@Repository
@SuppressWarnings("unchecked")
public class UserDetailsDaoImpl extends BaseRepositoryImpl<User, Long> implements UserDetailsDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsDaoImpl.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 7289512287861358569L;

	@Override
	public User getUserByUserName(String userName) {
		Query query = getEntityManager().createQuery("from User where userName='" + userName + "' and enabled is true");
		List list = query.getResultList();
		if (list.size() != 0)
			return (User) list.get(0);
		else
			return null;
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> rolesList = getEntityManager().createQuery("from Role").getResultList();
		return rolesList;
	}

	@Override
	public List<User> getActiveUsers() {
		List<User> usersList = getEntityManager().createQuery("from User where enabled is true").getResultList();
		return usersList;
	}

	@Override
	public List<User> getInActiveUsers() {
		List<User> usersList = getEntityManager().createQuery("from User where enabled is false").getResultList();
		return usersList;
	}

	@Override
	public void activateUserById(Long id) {
		Query query = getEntityManager().createQuery("update User set enabled is true where id=" + id);
		query.executeUpdate();

	}

	@Override
	public void deActivateUserById(Long id) {
		Query query = getEntityManager().createQuery("update User set enabled is false where id=" + id);
		query.executeUpdate();
	}

}
