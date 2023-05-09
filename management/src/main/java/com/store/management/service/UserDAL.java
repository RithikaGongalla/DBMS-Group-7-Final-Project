package com.store.management.service;

import java.util.HashMap;
import java.util.List;

import com.store.management.model.Orders;
import com.store.management.model.Products;
import com.store.management.model.Seller;
import com.store.management.model.Stocks;
import com.store.management.model.Users;
import com.store.management.vo.Response;

public interface UserDAL {

	Response<String> addUser(Users user);

	Response<Users> userLogin(Users user);

	Response<String> addSeller(Seller seller);

	Response<String> addSeller(List<Seller> seller);

	Response<String> addProduct(Products seller);

	Response<String> addProduct(List<Products> seller);

	Response<List<Products>> getProducts();

	Response<List<Seller>> getSellers();

	Response<String> orderItem(Orders orders);

	Response<String> addstock(Stocks stock);

	Response<HashMap> fetchSellerItems();

	Response<String> deleteSeller(Seller seller);

	Response<Products> fetchBrandedProduct(String brand, String product);

}
