package com.boram.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boram.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
	public Customer findCustomerByIdAndPassword(int id, String password);
	public Customer findCustomerById(int id);
}