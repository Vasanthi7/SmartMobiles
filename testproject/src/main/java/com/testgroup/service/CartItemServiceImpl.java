package com.testgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testgroup.dao.CartItemDAOInt;
import com.testgroup.model.CartItem;


@Service
public class CartItemServiceImpl implements CartItemServiceInt

{

	@Autowired
	private CartItemDAOInt cartitemdao;
	
	public void addCartItem(CartItem cartItem) {
		cartitemdao.addCartItem(cartItem);
	}

	public CartItem getCartItem(String cartItemId) {
		
		return cartitemdao.getCartItem(cartItemId);
	}

	public void removeCartItem(String cartItemId) {
		cartitemdao.removeCartItem(cartItemId);
	}

	public void removeAllCartItems(String customerid) {
		cartitemdao.removeAllCartItems(customerid);
	}



}

