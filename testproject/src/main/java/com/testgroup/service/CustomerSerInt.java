package com.testgroup.service;

import java.util.List;

import com.testgroup.model.Customer;
import com.testgroup.model.UserDetails1;
import com.testgroup.model.Vw_Prod_Supp_Xps;

public interface CustomerSerInt {

	public String addCustomer(Customer cust);
	public UserDetails1 logincheck(UserDetails1 ud);
	
	public List<Vw_Prod_Supp_Xps> getProductsForUser();
	
	public List<Vw_Prod_Supp_Xps> getAllSuppProd(String pid);
	
	
	public Customer getCustomerByUserID(String uid);
	
	public Customer getCustomerByID(String custid);

};
