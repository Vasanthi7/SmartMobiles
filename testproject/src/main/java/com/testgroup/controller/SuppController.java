package com.testgroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testgroup.model.Supplier;
import com.testgroup.service.SuppSerInt;

@Controller
public class SuppController 
{
	@Autowired
	private SuppSerInt supplierservice;

	
	
	
	@RequestMapping("/reqsupplierpage")
	public String displaysupplierpage(Model m)
	{
		m.addAttribute("supplierobject",new Supplier());
		return "supplierpage";
	}
	
	
	
	@RequestMapping("/reqSendSupplierData")
	public String addsupplierproduct(@ModelAttribute("supplierobject")Supplier su,Model m)

	{
		
		su.setIssupplieravailable(true);
		supplierservice.addsupplier(su);
		
	    return "redirect:reqDisplaySuppliersAdmin";
	}


	@RequestMapping("/reqDisplaySuppliersAdmin")
	public String displaysupplieradmin(Model m)
	{
		List<Supplier> supplierdata=supplierservice.getAllSuppliers();
		System.out.println(supplierdata);
		m.addAttribute("suppliers",supplierdata);
		return "displaysupplier";
	}
	
	
	
	
	
	
	@RequestMapping("/reqDeleteSupplier")
	public String deletesupplier(@RequestParam("sid")String sid,Model m)
	{
		supplierservice.deletesupplier(sid);
    	return "redirect:reqDisplaySuppliersAdmin";
	}
	
	
	
	@RequestMapping("/reqEditSupplier")
	public String editsupplier(@RequestParam("sid")String sid,Model m)
	{
		Supplier sup=supplierservice.getSupplierbyid(sid);
    	m.addAttribute("supplier",sup);
		return "editsupplier";
	}
	
	
	@RequestMapping("/reqUpdateSupplierData")
	public String editsupplierdb(@ModelAttribute("supplier")Supplier sup)
	{
		supplierservice.editSupplier(sup);
    	return "redirect:reqDisplaySuppliersAdmin";
	}
}
	
	


