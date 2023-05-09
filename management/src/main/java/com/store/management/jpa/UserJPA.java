package com.store.management.jpa;


import java.util.List;

import com.store.management.model.Orders;
import com.store.management.model.Products;
import com.store.management.model.Seller;
import com.store.management.model.Stocks;
import com.store.management.model.Users;
import com.store.management.vo.Response;



public interface UserJPA {

	public Response<String> addUser(Users user);

	public Response<Users> userLogin(Users user);

	public Response<Users> fetchUser(long userId);

	public Response<String> addSeller(Seller seller);

	public Response<String> addProduct(Products product);

	public Response<List<Products>> fetchProducts();

	public Response<List<Seller>> fetchSeller();

	public Response<String> orderItem(Orders orders);

	public Response<Stocks> checkStock(long productId);

	public Response<String> addStock(Stocks Stock);

	public Response<List<Stocks>> checkStock();

	public Seller findSeller(long id);


	public Response<Products> getProduct(String productName, long sellerId);

	public Seller findSeller(String name);


}
