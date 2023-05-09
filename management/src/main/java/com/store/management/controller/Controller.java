package com.store.management.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.management.model.Orders;
import com.store.management.model.Products;
import com.store.management.model.Seller;
import com.store.management.model.Stocks;
import com.store.management.model.Users;
import com.store.management.service.UserDAL;
import com.store.management.utils.MyList;
import com.store.management.vo.Response;

@RestController
//@RequestMapping("/user")
@CrossOrigin("*")
public class Controller {
	
	@Autowired
	private UserDAL userDAL;
	
	private final Logger log = LoggerFactory.getLogger(getClass());


	
	// API to sign up
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public Response<String> addNewUsers(@RequestBody Users user) {
		log.info("creating user.....");
		return userDAL.addUser(user);

	}
	
	
	// API to login
	@RequestMapping(value = "/log-in", method = RequestMethod.POST)
	public Response<Users> logIn(@RequestBody Users user) {
		log.info("signing user.....");
		return userDAL.userLogin(user);

	}
	

	// API to add a single seller
	@RequestMapping(value = "/add-seller", method = RequestMethod.POST)
	public Response<String> addSeller(@RequestBody Seller seller) {
	
		log.info("adding seller.....");
		return userDAL.addSeller(seller);

	}
	
	// API to add multiple sellers
	@RequestMapping(value = "/add-sellers", method = RequestMethod.POST)
	public Response<String> addSellers(@RequestBody List<Seller> seller) {
		log.info("adding seller.....");
		return userDAL.addSeller(seller);

	}
	
	// Api to add product
	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public Response<String> addProduct(@RequestBody Products seller) {
		log.info("adding seller.....");
		return userDAL.addProduct(seller);

	}
	
	// API to add multiple products
	@RequestMapping(value = "/add-products", method = RequestMethod.POST)
	public Response<String> addProduct(@RequestBody List<Products> seller) {
		log.info("adding seller.....");
		return userDAL.addProduct(seller);

	}
	
	// API to add stock
	@RequestMapping(value = "/add-stock", method = RequestMethod.POST)
	public Response<String> addProduct(@RequestBody Stocks stock) {
		log.info("adding stock.....");
		return userDAL.addstock(stock);

	}
	
	// API to fetch all products
	@RequestMapping(value = "/get-products", method = RequestMethod.GET)
	public Response<List<Products>> getProduct() {
		log.info("Fetching Products.....");
		return userDAL.getProducts();

	}

    // API to add sellers
	@RequestMapping(value = "/get-sellers", method = RequestMethod.GET)
	public Response<List<Seller>> getSeller() {
		log.info("Fetching sellers.....");
		return userDAL.getSellers();

	}
	
	// API to order an item
	@RequestMapping(value = "/order-item", method = RequestMethod.POST)
	public Response<String> orderItems(@RequestBody Orders order) {
		log.info("ordering items Products.....");
		return userDAL.orderItem(order);

	}
	
	// API to fetch seller wise items
	@RequestMapping(value = "/seller-item", method = RequestMethod.GET)
	public Response<HashMap> fetchSellerItems() {
		log.info("Felling all Products grouped by seller.....");
		return userDAL.fetchSellerItems();

	}
	
	// Api to remove seller
	@RequestMapping(value = "/remove-seller", method = RequestMethod.DELETE)
	public Response<String> deleteSeller(@RequestBody Seller seller) {
		seller.setActive(false);
		log.info("Dropping seller");
		return userDAL.deleteSeller(seller);

	}
	
	// API to fetch brand wise product
	@RequestMapping(value = "/fetch-product/{brand}/{product}", method = RequestMethod.GET)
	public Response <Products> fetchProduct(@PathVariable String brand , @PathVariable String product ){
		return userDAL.fetchBrandedProduct(brand,product);
		
	}
	
	
}