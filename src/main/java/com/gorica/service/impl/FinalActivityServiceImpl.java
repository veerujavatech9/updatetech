package com.gorica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorica.dao.FinalActivityDao;
import com.gorica.service.FinalActivityService;

@Service
public class FinalActivityServiceImpl implements FinalActivityService {

	@Autowired
	private FinalActivityDao finalActivityDao;

}
