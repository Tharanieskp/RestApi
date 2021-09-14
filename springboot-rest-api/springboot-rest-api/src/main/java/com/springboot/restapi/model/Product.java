package com.springboot.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "projects")
public class Product {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column(name = "product_name", nullable = false)
	private String productName;
	
	@Column(name = "brand", nullable = false)
	private String brand;
	
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "price", nullable = false)
	private int price;
	
	@Column(name="imageUrl", nullable = false)
	private String imageUrl;
	
	@Column(name="priceCurrency", nullable=false)
	private String priceCurrency;
	
	public Product() {
	}
	
	
	public Product(String productName, String brand, int size) {
		this.productName = productName;
		this.brand = brand;
		
	}
	
	public Product(String description, int price) {
		this.description = description;
		this.price = price;
	}
    
	public Product(String imageUrl,String priceCurrency) {
		this.imageUrl = imageUrl;
		this.priceCurrency = priceCurrency;
		}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	  
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getPriceCurrency() {
		return priceCurrency;
	}


	public void setPriceCurrency(String priceCurrency) {
		this.priceCurrency = priceCurrency;
	}


	@Override
	public String toString() {
		return "Product [productId=" + id + ", productName=" + productName + ",  brand=" + brand + ", productDesc=" + description +",imageUrl = " + imageUrl +",productPrice=" + price +",priceCurrency=" + priceCurrency +"]";
	}
	
}
