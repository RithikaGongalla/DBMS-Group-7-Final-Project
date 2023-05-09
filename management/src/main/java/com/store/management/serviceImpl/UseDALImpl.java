package com.store.management.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.management.jpa.UserJPA;
import com.store.management.model.Orders;
import com.store.management.model.Products;
import com.store.management.model.Seller;
import com.store.management.model.Stocks;
import com.store.management.model.Users;
import com.store.management.service.UserDAL;
import com.store.management.vo.Response;

@Component
public class UseDALImpl implements UserDAL {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserJPA userJPA;

	// Service used for adding a new user
	@Override
	public Response<String> addUser(Users user) {
		Response<String> response = new Response<String>();
		try {

			if (user.getRole() == null)
				user.setRole("user");
			if (user.getCreateAt() == null)
				user.setCreateAt(new Date());

			response = userJPA.addUser(user);

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	// Service used for login
	@Override
	public Response<Users> userLogin(Users user) {
		Response<Users> response = new Response<Users>();
		try {
			response = userJPA.userLogin(user);
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	// service to fetch user details based on userid
	public Response<Users> fetchuserdetails(long userId) {
		log.info("fetching user");
		Response<Users> response = new Response<Users>();
		try {
			response = userJPA.fetchUser(userId);

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	// service to add a new seller by validating admin role
	
	@Override
	public Response<String> addSeller(Seller seller) {

		Response<String> response = new Response<String>();
		try {
			log.info("VALIDATING USER");
			Response<Users> userResponse = fetchuserdetails(seller.getAddedBy());
			if (userResponse.getStatus() == "Success" && userResponse.getData() != null) {
				log.info("checking for user role");
				Users user = userResponse.getData();
				if (user.getRole().contentEquals("admin")) {
					seller.setActive(true);
					response = userJPA.addSeller(seller);
				} else {

					response.setStatus("Failed");
					response.setMessage("Only admin can add sellers");
				}
			} else {

				response.setStatus("Failed");
				response.setMessage("Failed to fetch adding user details");

			}

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	// service to add multiple sellers by validating admin role
	@Override
	public Response<String> addSeller(List<Seller> seller) {
		Response<String> response = new Response<String>();
		try {
			for (Seller s : seller) {
				s.setActive(true);
				response = addSeller(s);
			}
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	// service to add a new product by checking admin role
	@Override
	public Response<String> addProduct(Products product) {
		Response<String> response = new Response<String>();
		try {

			Response<Users> userResponse = fetchuserdetails(product.getAddedBy());
			if (userResponse.getStatus() == "Success" && userResponse.getData() != null) {
				Users user = userResponse.getData();
				if (user.getRole().contentEquals("admin")) {
					response = userJPA.addProduct(product);
				} else {

					response.setStatus("Failed");
					response.setMessage("Only admin can add sellers");
				}
			} else {

				response.setStatus("Failed");
				response.setMessage("Failed to fetch adding user details");

			}

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}
	
	
// service to add multiple products by validating admin role
	@Override
	public Response<String> addProduct(List<Products> product) {
		Response<String> response = new Response<String>();
		try {
			for (Products s : product) {
				response = addProduct(s);
			}
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<List<Products>> getProducts() {
		Response<List<Products>> response = new Response<List<Products>>();
		try {

			response = userJPA.fetchProducts();

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}
	
	
    // service to add list of sellers
	@Override
	public Response<List<Seller>> getSellers() {
		Response<List<Seller>> response = new Response<List<Seller>>();
		try {

			response = userJPA.fetchSeller();

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	// service to order an item 
	@Override
	public Response<String> orderItem(Orders orders) {
		Response<String> response = new Response<String>();
		try {

			Response<Stocks> Stockresponse = checkStock(orders.getProductId());
			if (Stockresponse.getStatus() == "Success" && Stockresponse.getData() != null) {
				Stocks stock = Stockresponse.getData();
				System.out.print(stock.getStockLeft());
				if (stock.getStockLeft() > 0) {
					response = userJPA.orderItem(orders);
					if (stock.getStockLeft() > 1) {
						stock.setStockLeft(stock.getStockLeft() - 1);
					} else {
						stock.setStockLeft((0));
					}
					addstock(stock);
				}

				else {
					response.setStatus("Failed");
					response.setMessage("Product Out of Stock");
				}
			} else {
				response.setStatus(Stockresponse.getStatus());
				response.setMessage("Failed to order Product (Out of Stock) ");
			}

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	public Response<Stocks> checkStock(long productId) {
		Response<Stocks> response = new Response<Stocks>();
		try {

			response = userJPA.checkStock(productId);
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	// service to add stock to a product
	@Override
	public Response<String> addstock(Stocks stock) {
		Response<String> response = new Response<String>();
		try {

			Response<Stocks> Stockresponse = checkStock(stock.getProductId());
			if (Stockresponse.getStatus() == "Success" && Stockresponse.getData() != null) {
				stock.setStockId(Stockresponse.getData().getStockId());
			}

			if (stock.getUpdatedAt() == null)
				stock.setUpdatedAt(new Date());

			response = userJPA.addStock(stock);

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}

	// service to fetch seller wise products
	@Override
	public Response<HashMap> fetchSellerItems() {
		Response<HashMap> response = new Response<HashMap>();
		HashMap<Long, String> sellerInfo = new HashMap<Long, String>();
		HashMap<String, ArrayList<String>> sellerItems = new HashMap<String, ArrayList<String>>();
		try {
			Response<List<Products>> products = getProducts();
			Response<List<Seller>> sellers = getSellers();
			if (products.getStatus() == "Success" && products.getData() != null && sellers.getStatus() == "Success"
					&& sellers.getData() != null) {
				List<Products> product = products.getData();
				List<Seller> seller = sellers.getData();

				for (Seller s : seller) {
					sellerInfo.put(s.getSellerId(), s.getSellerName());
				}

				for (Products p : product) {
					if (sellerItems.get(sellerInfo.get(p.getSellerId())) == null) {

						ArrayList<String> ls = new ArrayList<String>();
						ls.add(p.getProductName());
						sellerItems.put(sellerInfo.get(p.getSellerId()), ls);

					} else {
						ArrayList<String> ls = sellerItems.get(sellerInfo.get(p.getSellerId()));
						ls.add(p.getProductName());
						sellerItems.put(sellerInfo.get(p.getSellerId()), ls);
					}
				}
				response.setStatus("Success");
				response.setData(sellerItems);
				response.setMessage("Sucessfully fetched data");

			} else {
				response.setStatus("Failed");
				response.setMessage("Failed to fetch Seller or Products");
			}

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}
	
	
    // service to delete seller
	@Override
	public Response<String> deleteSeller(Seller s) {
		Response<String> response = new Response<String>();
		try {

			Seller seller = getSeller(s.getSellerId());
			if (seller != null) {
				seller.setActive(false);
				response = userJPA.addSeller(seller);

			} else {
				response.setStatus("Failed");
				response.setMessage("Invalid seller Id");
			}

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured" + e.getMessage());
			log.info(e.getMessage());
		}
		return response;
	}
	
	
    // supporting method to get seller based on seller id
	public Seller getSeller(long id) {
		Seller seller = new Seller();

		try {
			seller = userJPA.findSeller(id);

		} catch (Exception e) {

			log.info(e.getMessage());
		}
		return seller;
	}

	// supporting methood to fetch seller based on name
	public Seller getSeller(String name) {
		Seller seller = new Seller();

		try {
			seller = userJPA.findSeller(name);

		} catch (Exception e) {

			log.info(e.getMessage());
		}
		return seller;
	}

	// service to fetch a specific product from a specified brand
	@Override
	public Response<Products> fetchBrandedProduct(String brand, String product) {
		Response<Products> response = new Response<Products>();

		Response<HashMap> sellerresponse = fetchSellerItems();
		if (sellerresponse.getStatus() == "Success") {
			HashMap<String, ArrayList<String>> sellerItems = new HashMap<String, ArrayList<String>>();
			sellerItems = sellerresponse.getData();
			TreeSet<String> bst_brand = new TreeSet<>(sellerItems.keySet());
			if (bst_brand.contains(brand)) {
				TreeSet<String> bst_products = new TreeSet<>(sellerItems.get(brand));
				if (bst_products.contains(product)) {

					Seller details = getSeller(brand);
					response = userJPA.getProduct(product, details.getSellerId());

				} else {
					response.setStatus("Failed");
					response.setMessage("No such Product available from said brand");
				}

			} else {
				response.setStatus("Failed");
				response.setMessage("No such Brand details available");
			}
		} else {
			response.setStatus("Failed");
			response.setMessage("Failed to fetch details");
		}
		return response;
	}

}
