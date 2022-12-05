package com.boram.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boram.entity.Customer;
import com.boram.entity.Product;
import com.boram.entity.Receipt;
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

//	@Override
//	public Product purchaseItem(int productId, int amount) {
//		Product product = productDao.findById(productId).orElse(null);
//		
////		if (product == null) {
////			System.out.println("Product with id: " + productId + " does not exist");
////			return null;
////		}
//		
//		if (product.getStock() < amount) {
//			System.out.println("Product with id: " + productId + " does not have enough stock");
//			return null;
//		}
//		
//		product.setStock(product.getStock() - amount);
//		productDao.save(product);
//		return product;
//	}

	@Override
	public Collection<Product> getAllProducts() {
		return productDao.findAll();
	}


//	@Override
//	public Receipt generateReceipt(int id, int productId, int numOfItems) {
//		Customer customer = customerDao.findById(id).orElse(null);
//		Product product = productDao.findById(productId).orElse(null);
//		
//		if (numOfItems < (product.getStock())) {
//
//			// convert data type of quantity for finding total cost, store as new variable 
//			double doubleQ = (double) numOfItems;
//			
//			// find the total cost of the quantity of products wanted
//			double cost = doubleQ * (product.getCost());
//			
//			// set new products value for customer buying products
//			int prods = customer.getProducts();
//			prods = prods + numOfItems;
//			
//			// use DAO JPQL methods to update balance+products of customer and stock of product
//			int updateCB = customerDao.updateBalanceAndProducts(cost, prods, id);
//			int updatePS = productDao.updateStock(numOfItems, productId);
//			
//			// if the cost is not too expensive for the customer, and the update methods return a positive value (meaning they worked)
//			if (cost < (customer.getBalance()) && updateCB !=0 && updatePS !=0) {
//				return new Receipt(customer, product, numOfItems, cost);
//			}
//			else {
//				return null;
//			}
//		}
//		else {
//			return null;
//		}
//	}

	@Override
	public Product findProductById(int productId) {
		return productDao.findById(productId).orElse(null);
	}

	@Override
	public Receipt purchase(int productId, int amount, Customer customer) {
		Product product = productDao.findById(productId).orElse(null);
		
		if (product == null) {
			return null;
		}

		if (product.getStock() < amount) {
			return null;
		}
		
		productDao.updateStock(productId, product.getStock() - amount);
		
		Receipt receipt = new Receipt();
		receipt.setProduct(product);
		receipt.setNumOfItems(amount);
		return receipt;
	}


	
	
}
