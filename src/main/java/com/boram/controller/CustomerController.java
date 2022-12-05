package com.boram.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boram.entity.Customer;
import com.boram.entity.Product;
import com.boram.entity.Receipt;
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
			Collection<Product> products = customerService.getAllProducts();
			modelAndView.addObject("products", products);
			modelAndView.setViewName("ProductPage");
		} else {
			modelAndView.addObject("message", "Login Failed, Please try again");
			modelAndView.setViewName("Index");
		}
		
		return modelAndView;
	}
	
	@RequestMapping("/product")
	public ModelAndView product(@RequestParam("productId") int productId, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("BuyPage");
		Product product = customerService.findProductById(productId);
		
		modelAndView.addObject("product", product);
		
		return modelAndView;
	}
	
	@RequestMapping("/buy")
	public ModelAndView buy(@RequestParam("productId") int productId, @RequestParam("amount") int amount, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("Output");

		Customer customer = (Customer)session.getAttribute("customer");
		Receipt receipt = customerService.purchase(productId, amount, customer);
		
		if (receipt != null) {
			modelAndView.addObject("message", "All good");
			modelAndView.addObject("hasReceipt", true);
			modelAndView.addObject("receipt", receipt);
		} else {
			modelAndView.addObject("hasReceipt", false);
			modelAndView.addObject("message", "Not enough stock");
		}
		
		return modelAndView;
	}
	
}
