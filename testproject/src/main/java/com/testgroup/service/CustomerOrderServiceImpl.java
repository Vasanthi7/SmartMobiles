package com.testgroup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.testgroup.model.CustomerOrder;
import com.testgroup.model.CustomerOrderHistory;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderServiceInt {

	public List<CustomerOrder> custOrders(String custid) {
		// TODO Auto-generated method stub
		return null;
	}

	public float getRating(String productid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public CustomerOrderHistory getCustomerOrderHistoryByOrderHistoryId(String ordhistid) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNoOfReviews(String productid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNoOfItemsSold(String productid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void updateOrderHistory(CustomerOrderHistory coh) {
		// TODO Auto-generated method stub

	}

}
