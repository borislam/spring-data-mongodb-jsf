package com.borislam.view;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import com.borislam.domain.Product;
import com.borislam.service.ProductService;

@Component
@Scope("session")
public class ProductSearchBean {
	
	private Product selectedProduct;
	
	private ProductSearchCriteria criteria = new ProductSearchCriteria();
	
	private List<Product> productList;
	
	
	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public ProductSearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(ProductSearchCriteria criteria) {
		this.criteria = criteria;
	}
	@Autowired
	private ProductService productService;
	
	public void doSearch(ActionEvent event){
		productList= productService.searchByCriteria(criteria);
	}
	
	
	public String doEditDetail() {
		(FacesContext.getCurrentInstance().getExternalContext().getFlash()).put("selected", selectedProduct);
		return "detail.xhtml";
	}
	

	public void doDelete(ActionEvent event){
		try {			
			productService.deleteProduct(selectedProduct);
			
			FacesContext context = FacesContext.getCurrentInstance();  
	        context.addMessage(null, new FacesMessage("Delete Successfully!"));

		}
		catch (DataAccessException e ) {
			FacesContext context = FacesContext.getCurrentInstance();  
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error when deleting product!",null));
		}
		
	}
}
