package com.boram.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boram.entity.Customer;
import com.boram.model.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/")
	public ModelAndView getLoginPageController() {
		return new ModelAndView("Index");
	}
	
	@RequestMapping("/login")
	public ModelAndView loginController(@RequestParam("accountId") int id,@RequestParam("password") String password, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		Customer customer = customerService.login(id, password);
	
		if(customer!=null) {
			session.setAttribute("customer", customer);
			modelAndView.setViewName("ProductPage");
		} else {
			modelAndView.addObject("message", "Login Failed, Please try again");
			modelAndView.setViewName("Index");
		}
		
		return modelAndView;
	}
}
