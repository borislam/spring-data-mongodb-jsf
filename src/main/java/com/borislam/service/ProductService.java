package com.borislam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borislam.domain.Product;
import com.borislam.repository.ProductRepository;
import com.borislam.view.ProductSearchCriteria;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> searchByCriteria(ProductSearchCriteria criteria){
		return productRepository.searchByCriteria(criteria);
	}
	
	public Product getProduct(String sku) {
		return productRepository.findBySku(sku);
	}
		
	public void saveProduct(Product p){		
		productRepository.save(p);
	}
	
	public void deleteProduct(Product p){		
		productRepository.delete(p);
	}
	
}
