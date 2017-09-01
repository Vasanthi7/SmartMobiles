package com.testgroup.dao;

import java.util.List;

import com.testgroup.model.Product;

public interface ProdDAOInt {
	
	void addproduct(Product p);
	
	List<Product> getAllProducts();
	public void deleteproduct(String pid);
	
	
	public Product getProductbyid(String pid);
	public void editproduct(Product prd);
	
}
