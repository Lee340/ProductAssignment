package com.boram.model.service;

import java.util.Collection;

import com.boram.entity.Customer;
import com.boram.entity.Product;
import com.boram.entity.Receipt;

public interface CustomerService {
	
	Customer login(int id, String password);
	Collection<Product> getAllProducts();
	Product findProductById(int productId);
	Receipt purchase(int productId, int amount, Customer customer);
}
