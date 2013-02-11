package com.borislam.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import com.borislam.domain.Product;
import com.borislam.repository.ProductRepostitoryCustom;
import com.borislam.view.ProductSearchCriteria;


public class ProductRepositoryImpl implements ProductRepostitoryCustom{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Product> searchByCriteria(ProductSearchCriteria criteria) {
		Query query = new Query();
		if ( StringUtils.hasText(criteria.getSku())) 
		{	
			Criteria c = Criteria.where("sku").is(criteria.getSku());
			query.addCriteria(c);
		}
		if (StringUtils.hasText(criteria.getTitle())) {
			Criteria c = Criteria.where("title").regex(".*" + criteria.getTitle() + ".*", "i");
			query.addCriteria(c);
		}
		if (StringUtils.hasText(criteria.getDescription())) {
			Criteria c = Criteria.where("description").regex(".*" + criteria.getDescription() + ".*", "i");
			query.addCriteria(c);
		}
		if (StringUtils.hasText(criteria.getProductType())) {
			Criteria c = Criteria.where("type").is(criteria.getProductType());
			query.addCriteria(c);
			
		}		
		if (StringUtils.hasText(criteria.getTrack())) {
			Criteria c = Criteria.where("details.tracks").regex(".*" + criteria.getTrack() + ".*", "i");
			query.addCriteria(c);
		}
		if (StringUtils.hasText(criteria.getChapter())) {
			Criteria c = Criteria.where("details.chapters").regex(".*" + criteria.getChapter() + ".*", "i");
			query.addCriteria(c);
		}
		return mongoTemplate.find(query, Product.class);
	}
	
	
}
