package com.boram.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boram.entity.Customer;
import com.boram.entity.Product;
import com.boram.model.persistence.CustomerDao;
import com.boram.model.persistence.ProductDao;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired 
	CustomerDao customerDao;
	@Autowired 
	ProductDao productDao;

	@Override
	public Customer login(int id, String password) {
		try {
			Customer customer = customerDao.findCustomerByIdAndPassword(id, password);
			return customer;
		} catch (Exception exception) {
			return null;
		}
	}

	@Override
	public Product purchaseItem(int productId, int amount) {
		Product product = productDao.findById(productId).orElse(null);
		
		if (product == null) {
			System.out.println("Product with id: " + productId + " does not exist");
			return null;
		}
		
		if (product.getStock() < amount) {
			System.out.println("Product with id: " + productId + " does not have enough stock");
			return null;
		}
		
		product.setStock(product.getStock() - amount);
		productDao.save(product);
		return product;
	}
	
}
