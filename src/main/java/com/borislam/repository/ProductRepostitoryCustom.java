package com.borislam.repository;

import java.util.List;

import com.borislam.domain.Product;
import com.borislam.view.ProductSearchCriteria;

public interface ProductRepostitoryCustom {
	public List<Product> searchByCriteria(ProductSearchCriteria criteria);
	
}
