package com.testgroup.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testgroup.model.BillingAddress;
import com.testgroup.model.Cart;
import com.testgroup.model.Customer;
import com.testgroup.model.ShippingAddress;
import com.testgroup.model.UserDetails1;
import com.testgroup.model.Vw_Prod_Supp_Xps;
import com.testgroup.service.CustomerSerInt;

@Controller
public class CustomerController 
{
	
	
	@Autowired
	private CustomerSerInt customerService;
	
	@RequestMapping("/reqLogout")
	public String logout(HttpSession hsession, Model m) {
		hsession.invalidate();
		String logoutMessage = "Logged out succcessfully.\nThanks for visiting our site...";
		m.addAttribute("message", logoutMessage);
		return "index";
	}

	
	@RequestMapping("/reqSignupPage1")
	public String displaySignupForm(Model m) {
		UserDetails1 ud = new UserDetails1();
		ShippingAddress sad = new ShippingAddress();
		Customer cust = new Customer();
		cust.setUserDetails(ud);
		cust.setShippingAddress(sad);
		m.addAttribute("customer", cust);
		return "signuppage1";
	}

	@RequestMapping("/reqSendSignupData")
	public String sendSignUpData(@ModelAttribute("customer") Customer cust, Model m) {

		cust.setEnabled(true);
		cust.getUserDetails().setUserrole("ROLE_USER");
		cust.getUserDetails().setEnabled(true);

		BillingAddress bad = new BillingAddress();
		bad.setHouseno(cust.getShippingAddress().getHouseno());
		bad.setStreet(cust.getShippingAddress().getStreet());
		bad.setArea(cust.getShippingAddress().getArea());
		bad.setCity(cust.getShippingAddress().getCity());
		bad.setState(cust.getShippingAddress().getState());
		bad.setCountry(cust.getShippingAddress().getCountry());
		bad.setPincode(cust.getShippingAddress().getPincode());

		Cart crt = new Cart();

		cust.setBillingAddress(bad);
		cust.setCart(crt);

		String userid = customerService.addCustomer(cust);
		String message = "Signup is successfull.\nNew User id : " + userid;
		m.addAttribute("signupmsg", message);
		m.addAttribute("userObject", new UserDetails1());
		return "loginpageuser";
	}

	
	
	@RequestMapping("/reqLoginCheck")
	public String loginCheck(HttpSession hsession,@ModelAttribute("userObject") UserDetails1 ud, Model m)
	{
		
		
		UserDetails1 temp = customerService.logincheck(ud);
		System.out.println(temp);
		if (temp == null) {// if authentication failed
			String message = "Login failed..,\nTry again...";
			m.addAttribute("userObject", new UserDetails1());
			m.addAttribute("errormessage", message);
			return "loginpage";
		}

		
		else if (temp.getUserrole().equals("ROLE_USER"))
		{			
				List <Vw_Prod_Supp_Xps> productsuser = customerService.getProductsForUser();
				hsession.setAttribute("customerprofile", customerService.getCustomerByUserID(temp.getUserid()));

				m.addAttribute("productsuser", productsuser);
				System.out.println("data in controller  :" + productsuser);
			    return "userhomepage";
		}
	
		
		else 
		{
			
			hsession.setAttribute("adminprofile",customerService.getCustomerByUserID(temp.getUserid()));
			return "adminhomepage";
		}
		
	}
		@RequestMapping("/reqProductAllSuppliers")
		public String getProductsAllSuppliers(@RequestParam("pid")String pid,Model m){
			List<Vw_Prod_Supp_Xps> allSupProd = customerService.getAllSuppProd(pid);
			m.addAttribute("allSupProd", allSupProd);
			return "productsAllSuppliers";
		}
		
		
		
		
		//navbar code 
		
		@RequestMapping("/reqDisplayProductsUser")
		public String displayProductsUser(Model m,HttpSession hsession){
			Customer oldcust = (Customer) hsession.getAttribute("customerprofile");
			Customer newcust = customerService.getCustomerByID(oldcust.getCustomerid());
			hsession.setAttribute("customerprofile", newcust);

			List <Vw_Prod_Supp_Xps> productsuser = customerService.getProductsForUser();
			m.addAttribute("productsuser", productsuser);
			return "userhomepage";
		}
		
		
		
		
		

		@RequestMapping("/reqLogoutSpring") // spring security logout
		public String logoutSpring(HttpSession hsession, Model m) {
			hsession.invalidate();
			String logoutMessage = "Logged out succcessfully.\nThanks for visiting our site...";
			m.addAttribute("message", logoutMessage);
			return "index";
		}
		
		
		
		
		
		
		
		
		

		/// spring security authentication
		
		
		
		@RequestMapping("/reqLoginPage1")
		public String loginPage1(@RequestParam(value = "error", required = false) String error, Model m) {
			if (error != null) {
				String message = "Login failed..,\nTry again...";
				m.addAttribute("errormsg", message);
			}
			return "springSecurityLoginpage";
		}

		@RequestMapping("/springLoginCheck") // comes here after spring security
												// authenticates user
		public String loginCheck(Principal principal, HttpSession hsession, Model m) {

			System.out.print("\nCustomerController - springLoginCheck()");
			System.out.println("\nName : " + principal.getName());
			Customer customer = customerService.getCustomerByUserID(principal.getName());
			UserDetails1 userDetials1 = customer.getUserDetails();
			System.out.println("\nRole : " + userDetials1.getUserrole());

			if (userDetials1.getUserrole().equals("ROLE_USER")) {
				hsession.setAttribute("customerprofile", customer);
				return "redirect:/reqDisplayProductsUser";
			}

			if (userDetials1.getUserrole().equals("ROLE_ADMIN")) {
				hsession.setAttribute("adminprofile", customer);
				return "adminhomepage";
			}

			return "";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//validation for signuppage1
		
		
		
		@RequestMapping("/reqSendSignupData1")
		public String sendSignUpData1(@Valid @ModelAttribute("customer") Customer cust,BindingResult result ,Model m)
		{
			if(result.hasErrors())
			{
				System.out.println("\nError");
				m.addAttribute("customer", cust);
				return "signuppage1";
			}

			cust.setEnabled(true);
			cust.getUserDetails().setUserrole("ROLE_USER");
			cust.getUserDetails().setEnabled(true);

			BillingAddress bad = new BillingAddress();
			bad.setHouseno(cust.getShippingAddress().getHouseno());
			bad.setStreet(cust.getShippingAddress().getStreet());
			bad.setArea(cust.getShippingAddress().getArea());
			bad.setCity(cust.getShippingAddress().getCity());
			bad.setState(cust.getShippingAddress().getState());
			bad.setCountry(cust.getShippingAddress().getCountry());
			bad.setPincode(cust.getShippingAddress().getPincode());

			Cart crt = new Cart();

			cust.setBillingAddress(bad);
			cust.setCart(crt);

			String userid = customerService.addCustomer(cust);
			String message = "Signup is successfull.\nNew User id : " + userid;
			m.addAttribute("signupmsg", message);
			m.addAttribute("userObject", new UserDetails1());
			return "loginpageuser";
		}




	}
