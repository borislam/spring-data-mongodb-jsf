package com.borislam.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.borislam.domain.Product;

public interface ProductRepository  extends PagingAndSortingRepository<Product, String> , ProductRepostitoryCustom{

	
	

	
	List<Product> findByType(String type);
	
	List<Product> findByTypeAndTitle(String type, String title);
	
	
	
	Product findBySku(String sku);
}
