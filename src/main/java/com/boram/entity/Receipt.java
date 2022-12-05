package com.boram.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Receipt {
		
	private Product product;
	private int numOfItems;
	
	public double getTotal() {
		return product.getCost() * numOfItems;
	}
		
}
