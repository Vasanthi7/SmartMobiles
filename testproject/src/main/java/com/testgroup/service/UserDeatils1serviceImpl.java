package com.testgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testgroup.dao.UserDeatils1DAOInt;
import com.testgroup.model.UserDetails1;


@Service
public class UserDeatils1serviceImpl implements UserDetails1serviceInt {
     
	@Autowired
	private UserDeatils1DAOInt userDetailsDao;
	
	public String adduser(UserDetails1 ud) 
	{
		return userDetailsDao.addUser(ud);

	}
	public UserDetails1 loginCheck(UserDetails1 ud)
	{
		return userDetailsDao.loginCheck(ud);
	}
}
