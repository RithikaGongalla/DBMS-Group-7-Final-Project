package com.store.management.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	
	private long sellerId;

	private String productName;
	
	private String  productDescription;
	
	private float  price;
	
	private Date insertedAt;
	
	private long addedBy;

	public Products(long userId, long seller_id, String productName, String productDescription, float price,
			Date inserted_at, long addedBy) {
		super();
		this.productId = userId;
		this.sellerId = seller_id;
		this.productName = productName;
		this.productDescription = productDescription;
		this.price = price;
		this.setAddedBy(addedBy);
		this.insertedAt = inserted_at;
	}

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long seller_id) {
		this.sellerId = seller_id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Date getInsertedAt() {
		return insertedAt;
	}

	public void setInsertedAt(Date insertedAt) {
		this.insertedAt = insertedAt;
	}

	public long getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(long addedBy) {
		this.addedBy = addedBy;
	}

	
	

}

