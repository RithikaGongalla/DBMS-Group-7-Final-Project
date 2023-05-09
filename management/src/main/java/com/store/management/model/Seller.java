package com.store.management.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sellerId;
	
	private String sellerName;
	
	private String sellerIdentificationNumber;
	
	private String emailId;
	
	private String phoneNumber;

	private String address;
	
	private String mainCategory;
	
	private Boolean fbaSeller;
	
	private float sellerRating;
	
	private Date insertedAt;
	
	private boolean isActive;
	
	private long addedBy;

	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Seller( long sellerId, String sellerName, String sellerIdentificationNumber, String emailId, String phone_number, long addedBy,
			String address, String mainCategory, Boolean fbaSeller, float sellerRating, Date insertedAt,boolean isActive) {
		super();
		this.sellerName = sellerName;
		this.sellerId = sellerId;
		this.sellerIdentificationNumber = sellerIdentificationNumber;
		this.emailId = emailId;
		this.phoneNumber = phone_number;
		this.address = address;
		this.mainCategory = mainCategory;
		this.fbaSeller = fbaSeller;
		this.sellerRating = sellerRating;
		this.insertedAt = insertedAt;
		this.addedBy = addedBy;
		this.isActive = isActive;
	}


	public long getSellerId() {
		return sellerId;
	}


	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}


	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerIdentificationNumber() {
		return sellerIdentificationNumber;
	}

	public void setSellerIdentificationNumber(String sellerIdentificationNumber) {
		this.sellerIdentificationNumber = sellerIdentificationNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phone_number) {
		this.phoneNumber = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

	public Boolean getFbaSeller() {
		return fbaSeller;
	}

	public void setFbaSeller(Boolean fbaSeller) {
		this.fbaSeller = fbaSeller;
	}

	public float getSellerRating() {
		return sellerRating;
	}

	public void setSellerRating(float sellerRating) {
		this.sellerRating = sellerRating;
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


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	

}


