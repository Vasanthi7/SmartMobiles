package com.testgroup.service;

import java.util.List;

import com.testgroup.model.Product;

public interface ProdSerInt 

{

	void addproduct(Product p);
	

	List<Product> getAllProducts();
	
	
	public void deleteproduct(String pid);
	public Product getProductbyid(String pid);
	public void editproduct(Product prd);
	
}
