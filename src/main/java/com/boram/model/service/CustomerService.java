package com.boram.model.service;

import java.util.Collection;

import com.boram.entity.Customer;
import com.boram.entity.Product;

public interface CustomerService {
	
	Customer login(int id, String password);
	Product purchaseItem(int productId, int amount);
	Collection<Product> getAllProducts();
}
