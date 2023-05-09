package com.store.management.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	
	private long userId;
	
	private long productId;
	
	private String orderStatus;
	
	private Date orderedAt;
	
	private float productRating;
	
	private String productReview;
	
	private String modeOfPayment;
	
	private Date modifiedAt;

	public Orders(long orderId, long userId, long productId, String orderStatus, Date orderedAt, float productRating,
			String productReview, String modeOfPayment, Date modifiedAt) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.orderStatus = orderStatus;
		this.orderedAt = orderedAt;
		this.productRating = productRating;
		this.productReview = productReview;
		this.modeOfPayment = modeOfPayment;
		this.modifiedAt = modifiedAt;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(Date orderedAt) {
		this.orderedAt = orderedAt;
	}

	public float getProductRating() {
		return productRating;
	}

	public void setProductRating(float productRating) {
		this.productRating = productRating;
	}

	public String getProductReview() {
		return productReview;
	}

	public void setProductReview(String productReview) {
		this.productReview = productReview;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
   

}
