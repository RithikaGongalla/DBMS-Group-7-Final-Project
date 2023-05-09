package com.store.management.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stocks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long stockId;
	
	private long productId;
	
	private long stockLeft;
	
	private Date updatedAt;

	public Stocks() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Stocks(long stockId, long productId, long stockLeft, Date updatedAt) {
		super();
		this.stockId = stockId;
		this.productId = productId;
		this.stockLeft = stockLeft;
		this.updatedAt = updatedAt;
	}



	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getStockLeft() {
		return stockLeft;
	}

	public void setStockLeft(long stockLeft) {
		this.stockLeft = stockLeft;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
