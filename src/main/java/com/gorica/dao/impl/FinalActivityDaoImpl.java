package com.gorica.dao.impl;

import org.springframework.stereotype.Repository;

import com.gorica.dao.FinalActivityDao;
import com.gorica.dao.model.FinalActivityDetails;
import com.gorica.dao.repository.BaseRepositoryImpl;

@Repository
public class FinalActivityDaoImpl extends BaseRepositoryImpl<FinalActivityDetails, Long> implements FinalActivityDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8930739533324965719L;

}
