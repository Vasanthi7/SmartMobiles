package com.testgroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testgroup.dao.ProdDAOInt;
import com.testgroup.model.Product;
import com.testgroup.service.ProdSerInt;

@Controller
public class ProdController 
{
	
	

	@Autowired
	private ProdSerInt ProductService;
	
  @RequestMapping("/reqaddproduct")
  public String displayproductpage(Model m)
	{
		m.addAttribute("productobject",new Product());
		return "productpage";
	}
  
  
  
  
  
	

  
  @RequestMapping("/reqSendProductData")
  public String addProductToDB(@ModelAttribute("productobject")Product p,Model m)

	{

		p.setIsproductavailable(true);
		ProductService.addproduct(p);

		return "redirect:reqdisplayproducts";
	}
  
  
  
  @RequestMapping("/reqdisplayproducts")
  public String displayproductadmin(Model m)
	{
		List<Product> productdata=ProductService.getAllProducts();
		m.addAttribute("products",productdata);
		return "displayProductsAdmin";
	}
  
  
  
	
  @RequestMapping("/reqDeleteProduct")
  public String deleteproduct(@RequestParam("pid")String pid,Model m)
  {
  	ProductService.deleteproduct(pid);
  	return "redirect:reqdisplayproducts";
  }
  
  
  
  
  @RequestMapping("/reqEditProduct")
  public String editproduct(@RequestParam("pid")String pid,Model m)
  {
  	Product prd=ProductService.getProductbyid(pid);
  	m.addAttribute("product",prd);
  	return "editproduct";
  }
  
  
  @RequestMapping("/reqUpdateProductData")
  public String editproductdb(@ModelAttribute("product")Product prd)
  {
  	ProductService.editproduct(prd);
  	return "redirect:reqdisplayproducts";
  }
}
