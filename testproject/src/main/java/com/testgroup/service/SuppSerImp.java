package com.testgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testgroup.dao.SuppDaoInt;
import com.testgroup.model.Supplier;


@Service
public class SuppSerImp implements SuppSerInt {
	
	@Autowired
    private SuppDaoInt supplierDao;
    
    
	public void addsupplier(Supplier su) 
	{
		supplierDao.addsupplier(su);
		
		
	}
	
	
	public List<Supplier> getAllSuppliers()
	{
		return supplierDao.getAllSuppliers();
	}


	public void deletesupplier(String sid)
	{
		
		supplierDao.deletesupplier(sid);
		
	}


	public Supplier getSupplierbyid(String sid) 
	{
		return supplierDao.getSupplierbyid(sid);
	}


	public void editSupplier(Supplier sup) 
	{
        supplierDao.editSupplier(sup);	
		
	}
	
	
	
	
	
	

}
