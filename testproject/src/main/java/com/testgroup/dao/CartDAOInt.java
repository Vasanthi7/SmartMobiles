package com.testgroup.dao;

import com.testgroup.model.Cart;

public interface CartDAOInt
{

		public abstract Cart getCart(String cartid); 
		public abstract int getCartSize(Cart cart);
		public abstract void updateCart(Cart cart);
		
		
	}

