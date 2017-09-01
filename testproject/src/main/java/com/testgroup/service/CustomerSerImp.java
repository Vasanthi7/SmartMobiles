package com.testgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testgroup.dao.CustomerDAOInt;
import com.testgroup.model.Customer;
import com.testgroup.model.UserDetails1;
import com.testgroup.model.Vw_Prod_Supp_Xps;
@Service
public class CustomerSerImp implements CustomerSerInt {
	

	@Autowired
	private CustomerDAOInt customerDao;


	public String addCustomer(Customer cust) {
		return customerDao.addCustomer(cust);
	}


	public UserDetails1 logincheck(UserDetails1 ud) {
		return customerDao.logincheck(ud);
	}
	
	public List<Vw_Prod_Supp_Xps> getProductsForUser() {
		return customerDao.getProductsForUser();
	}
	
	
	public List<Vw_Prod_Supp_Xps> getAllSuppProd(String pid) {		
		return customerDao.getAllSuppProd(pid);
	}


	public Customer getCustomerByUserID(String uid)
	{
	   return 	customerDao.getCustomerByUserID(uid);
	}


	public Customer getCustomerByID(String custid)
	{
		return customerDao.getCustomerByID(custid);
	}

	
	
	

}



