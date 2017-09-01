package com.testgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testgroup.model.UserDetails;
import com.testgroup.model.UserDetails1;
import com.testgroup.service.UserDetails1serviceInt;

@Controller
public class MyController {

	// Open your previous project folder in mycomputer
	@Autowired
	private UserDetails1serviceInt userService;
	
	
	@RequestMapping("/")
	public String displayHomePage(){
		return "index";
	}
	
	@RequestMapping("/reqSignupPage")
	public String displaySignupPage(Model m){
		m.addAttribute("userobject", new UserDetails1());
		return "signuppage";
		
	}
	
	@RequestMapping("/reqSendSignupDatatoDB")
	public String sendSignupdatatodb(@ModelAttribute("userobject")UserDetails1 ud,Model m){
		System.out.println(ud.getUserid());
		System.out.println(ud.getPassword());
		ud.setUserrole("ROLE_USER");
		ud.setEnabled(true);
		String uid = userService.adduser(ud);
		System.out.println(uid);
		String message = "Signup successfull. New user id : " +uid;	
        m.addAttribute("signupmessage",message); 
		m.addAttribute("userobject", new UserDetails1());
		return "loginpage";
	}
	

	@RequestMapping("/reqLoginPage")
	public String displayloginpage(Model m)
	{
		m.addAttribute("userObject",new UserDetails1());
		return "loginpageuser";
	}

	@RequestMapping("/reqSendLoginData")
	public String loginCheck(@ModelAttribute("userobject")UserDetails1 ud,Model m){
		UserDetails1 temp = userService.loginCheck(ud);
		// if login is succes - loginsuccess
		// else display loginerror page
		System.out.println(temp);
		if(temp==null) { // authentication failed
			String errormessage = "Login failed. Try again...";
			m.addAttribute("errormessage", errormessage);
			m.addAttribute("userobject",new UserDetails1());
			return "loginpage";
		}
		else if(temp.getUserrole().equals("ROLE_USER")){ // authentication success-user
			m.addAttribute("userdata", temp);
			return "loginsuccessuser";
		}
		else{
			m.addAttribute("userdata", temp);
			return "loginsuccessadmin";
		}
		
	}
	
	
	

	

}
