package com.borislam.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.dao.DataAccessException;

import com.borislam.domain.Detail;
import com.borislam.domain.Pricing;
import com.borislam.domain.Product;
import com.borislam.service.ProductService;

@Component
@Scope("session")
public class ProductDetailBean {
	
	@Autowired
	private ProductService productService;
	private boolean newProduct;
	private Product product;
	private String newTrack;
	private String newChapter;
	
	
	
	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	public String getNewTrack() {
		return newTrack;
	}

	public void setNewTrack(String newTrack) {
		this.newTrack = newTrack;
	}

	public String getNewChapter() {
		return newChapter;
	}

	public void setNewChapter(String newChapter) {
		this.newChapter = newChapter;
	}

	public void initProduct(){
		Object selectedProduct = (FacesContext.getCurrentInstance().getExternalContext().getFlash()).get("selected");
		
		if (selectedProduct==null &&  !FacesContext.getCurrentInstance().isPostback()) {
			product = new Product();
			product.setDetails(new Detail());
			product.setPricing(new Pricing(0,0));
			setNewProduct(true);
		}
		if (selectedProduct!=null) {
			product = (Product)selectedProduct;
			setNewProduct(false);
		}
		
	}
	
	public void  doSave(ActionEvent event) {

		try {
			productService.saveProduct(product);
			
			FacesContext context = FacesContext.getCurrentInstance();  
	        context.addMessage(null, new FacesMessage("Save Successfully!"));
		}
		catch (DataAccessException e)
		{	
			e.printStackTrace();
			
			FacesContext context = FacesContext.getCurrentInstance();  
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error when saving product!",null));
			
		}
		  
	}
	
	public void  doAddTracks(ActionEvent event) {
		List<String> tracks = product.getDetails().getTracks();
		if (CollectionUtils.isEmpty(tracks)) {
			product.getDetails().setTracks(new ArrayList<String>());
		}
		product.getDetails().getTracks().add(this.newTrack);

	}
	
	public void  doAddChapters(ActionEvent event) {
		List<String> tracks = product.getDetails().getChapters();
		if (CollectionUtils.isEmpty(tracks)) {
			product.getDetails().setChapters(new ArrayList<String>() );
		}
		product.getDetails().getChapters().add(this.newChapter);

	}
	
	public void clearDetails(ValueChangeEvent  event) {

		if ("Audio Album".equalsIgnoreCase(event.getNewValue().toString()) ) {
			product.getDetails().setChapters(null);
		}
		if ("Book".equalsIgnoreCase( event.getNewValue().toString())) {
			product.getDetails().setTracks(null);
		}
	}
}
