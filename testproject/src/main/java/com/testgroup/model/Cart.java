package com.testgroup.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart implements Serializable
{

	@Id
	private String cartid;
	private double totalbill;
	
	
	@OneToMany(mappedBy="cart",cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	private List<CartItem> cartItem;
	
	
	
	
	@OneToOne(mappedBy="cart")
	private Customer customer;

	
	
	
	
	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public double getTotalbill() {
		return totalbill;
	}

	public void setTotalbill(double totalbill) {
		this.totalbill = totalbill;
	}

	public List<CartItem> getCartItems() {
		return cartItem;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItem = cartItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
	
	