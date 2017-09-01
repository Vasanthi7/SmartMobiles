package com.testgroup.service;

import java.util.List;

import com.testgroup.model.CustomerOrder;
import com.testgroup.model.CustomerOrderHistory;

public interface CustomerOrderServiceInt {

	
		public List<CustomerOrder> custOrders(String custid);
		//public void addProductReview(ProductReview pr);
		public float getRating(String productid);
		public CustomerOrderHistory getCustomerOrderHistoryByOrderHistoryId(String ordhistid);
		public int getNoOfReviews(String productid);
		public int getNoOfItemsSold(String productid);
	    //public List<Vw_Cust_Rating> getCommentsForProduct(String productid);
		public void updateOrderHistory(CustomerOrderHistory coh);
	}


