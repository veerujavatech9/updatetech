package com.gorica.dao.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public interface BaseRepositoryI<T, ID extends Serializable>{
	
	public EntityManager getSession();
	public T findOne( Long id );
	public List< T > findAll();
	public void save( T entity );
	public void update( T entity );
	public void delete( T entity );
	public void deleteById( Long entityId );

}
