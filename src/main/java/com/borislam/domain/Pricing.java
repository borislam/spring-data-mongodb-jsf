package com.borislam.domain;

public class Pricing {
	private String id;
	private double list;
	private double retail;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getList() {
		return list;
	}
	public void setList(double list) {
		this.list = list;
	}
	public double getRetail() {
		return retail;
	}
	public void setRetail(double retail) {
		this.retail = retail;
	}
	public Pricing(double list, double retail) {
		super();
		this.list = list;
		this.retail = retail;
	}
	
}
