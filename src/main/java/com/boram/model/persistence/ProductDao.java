package com.boram.model.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boram.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {	
//	JPQL
	@Modifying
	@Transactional
	@Query("update Product set stock = stock - :numOfItems where productId=:id")
	int updateStock(@Param("id") int id, @Param("numOfItems") int numOfItems);
}