package com.testgroup.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testgroup.model.Product;
import com.testgroup.model.Supplier;
import com.testgroup.model.XMAP_Product_Supplier;
import com.testgroup.service.ProdSerInt;
import com.testgroup.service.SuppSerInt;
import com.testgroup.service.XmapSerInt;

@Controller
public class XmapController {
	
	
	
	@Autowired
	private XmapSerInt xpsService;	
	@Autowired
	private ProdSerInt productService;
	
	@Autowired
	private SuppSerInt supplierService;
	
	
	@RequestMapping("/reqDisplayXps")
	public String displaySuppliers(Model m,HttpSession hsession){
		List<XMAP_Product_Supplier> data = xpsService.getAllXps();
		m.addAttribute("xpsdata", data);
		return "displayXps";
	}
	
	
	@RequestMapping("/reqAddProdSuppForm")
	public String addSupplierForm(Model m){	
		List<Product> prdata = productService.getAllProducts();
		List<Supplier> supdata = supplierService.getAllSuppliers();
		m.addAttribute("products", prdata);
		m.addAttribute("suppliers", supdata);
		m.addAttribute("prodsuppObject", new XMAP_Product_Supplier());
		return "addProdSupp";
}
	
	@RequestMapping("/reqAddProdSuppDataToDb")
	
		public String addProdSuppToDb(@ModelAttribute("prodsuppObject")XMAP_Product_Supplier xps,Model m)
		{
			xpsService.addxps(xps);
			String xpsmessage="XPS Record added successfully...";
			m.addAttribute("xpsmessage", xpsmessage);
			List<XMAP_Product_Supplier> data = xpsService.getAllXps();
			m.addAttribute("xpsdata", data);
			return "displayXps";
	
	}
	
	
	
	@RequestMapping("/reqDeleteXps")
	public String deleteXps(@RequestParam("psid")String psid,Model m,HttpSession hsession){
		xpsService.deleteXps(psid);	
		String xpsmessage="XPS Record deleted successfully...";
		m.addAttribute("xpsmessage", xpsmessage);
		List<XMAP_Product_Supplier> data = xpsService.getAllXps();
		m.addAttribute("xpsdata", data);
		return "displayXps";
	}
	

	@RequestMapping("/reqEditXpsPage")
	public String editXPSPage(@RequestParam("psid")String psid,Model m){
		XMAP_Product_Supplier xps = xpsService.getXpsById(psid);	
		String pname  = productService.getProductbyid(xps.getProductid()).getProductname();
		String supname = supplierService.getSupplierbyid(xps.getSupplierid()).getSuppliername();
		
		m.addAttribute("xps", xps);	
		m.addAttribute("pname", pname);	
		m.addAttribute("supname", supname);	
		
		return "editXps";
	}
	
	@RequestMapping("/reqEditXpsToDB")
	public String editXPStoDB(@ModelAttribute("prodsuppObject")XMAP_Product_Supplier xps){
		xpsService.editXps(xps);			
		return "redirect:/reqDisplayXps";
	}
	
}


