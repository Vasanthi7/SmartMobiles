package com.testgroup.dao;

import java.util.List;

import com.testgroup.model.Supplier;

public interface SuppDaoInt 

{

	void addsupplier(Supplier su);
	 List<Supplier> getAllSuppliers();
	public void deletesupplier(String sid);
	public Supplier getSupplierbyid(String sid);
	public void editSupplier(Supplier sup);
	 


}
