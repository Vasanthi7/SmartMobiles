package com.testgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testgroup.dao.ProdDAOInt;
import com.testgroup.model.Product;


@Service
public class ProdSerImp implements ProdSerInt
{
	
	@Autowired
	private ProdDAOInt productDao;

	public void addproduct(Product p)
	{
		productDao.addproduct(p);
	}

	public List<Product> getAllProducts()
	{
		return productDao.getAllProducts();
	}
	
	public void deleteproduct(String pid)
	{
		productDao.deleteproduct(pid);
	}
	
	
	public Product getProductbyid(String pid)
	{
	
		return productDao.getProductbyid(pid);
	}

	public void editproduct(Product prd) 
	{
		
		productDao.editproduct(prd);
	}
	
	
	
}
