package com.store.management.jpaImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.store.management.jpa.UserJPA;
import com.store.management.model.Orders;
import com.store.management.model.Products;
import com.store.management.model.Seller;
import com.store.management.model.Stocks;
import com.store.management.model.Users;
import com.store.management.repository.OrdersRepository;
import com.store.management.repository.ProductRepository;
import com.store.management.repository.SellerRepository;
import com.store.management.repository.StocksRepository;
import com.store.management.repository.UserRepository;
import com.store.management.vo.Response;

@Repository
public class UserJPAImpl implements UserJPA {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrdersRepository orderRepository;

	@Autowired
	private StocksRepository stocksRepository;
	
	
    // JPA to add a new user
	@Transactional
	@Override
	public Response<String> addUser(Users user) {
		Response<String> response = new Response<String>();
		try {
			user = userRepository.save(user);
			response.setStatus("Success");
			response.setMessage("User Saved Successfully");

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;
	}
	
	
    // JPA used to validate and login the user
	@Override
	public Response<Users> userLogin(Users user) {
		Response<Users> response = new Response<Users>();
		try {
			Users fetchedUser = userRepository.findFirstByUserName(user.getUserName());
			if (fetchedUser != null) {
				if (fetchedUser.getPassword().contentEquals(user.getPassword())) {
					response.setData(fetchedUser);
					response.setStatus("Success");
					response.setMessage("User Logged in Successfully");
				} else {
					response.setStatus("Failed");
					response.setMessage("Invalid username or Password");
				}

			} else {
				response.setStatus("Failed");
				response.setMessage("User Not Found");
			}
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;
	}

	// JPA to fetch the user based on userid
	@Override
	public Response<Users> fetchUser(long userId) {
		Response<Users> response = new Response<Users>();
		try {
			Users user = userRepository.findFirstByUserId(userId);
			response.setStatus("Success");
			response.setData(user);
			response.setMessage("User data fetched Successfully");

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception Occured");
		}
		return response;
	}

	// JPA to add a single seller
	@Override
	public Response<String> addSeller(Seller seller) {
		Response<String> response = new Response<String>();
		try {
			String opp;
			
			System.out.println("hey "+seller.isActive());

			if (seller.isActive() == true)
				opp = "Saved";
			else
				opp = "Deleted";

			String errMsg = String.format("Seller %s Successfully ", opp);

			sellerRepository.save(seller);
			response.setStatus("Success");
			response.setMessage(errMsg);

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;

	}

	// JPA to add a single product
	@Override
	public Response<String> addProduct(Products product) {
		Response<String> response = new Response<String>();
		try {
			productRepository.save(product);
			response.setStatus("Success");
			response.setMessage("Product Saved Successfully");

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;

	}
	
     // JPA to fetch all the products
	@Override
	public Response<List<Products>> fetchProducts() {
		Response<List<Products>> response = new Response<List<Products>>();
		try {
			List<Products> product = productRepository.findAll();
			response.setStatus("Success");
			response.setData(product);
			response.setMessage("Product Saved Successfully");

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;

	}

	// JPA to fetch all the sellers
	@Override
	public Response<List<Seller>> fetchSeller() {
		Response<List<Seller>> response = new Response<List<Seller>>();
		try {
			List<Seller> seller = sellerRepository.findAll();
			response.setStatus("Success");
			response.setData(seller);
			response.setMessage("Seller Saved Successfully");
		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;

	}

	// JPA to order an item
	@Override
	public Response<String> orderItem(Orders orders) {
		Response<String> response = new Response<String>();
		try {
			orderRepository.save(orders);
			response.setStatus("Success");
			response.setMessage("Order Placed Successfully");

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;

	}

	// JPA To add stock
	@Override
	public Response<String> addStock(Stocks Stock) {
		Response<String> response = new Response<String>();
		try {
			stocksRepository.save(Stock);
			response.setStatus("Success");
			response.setMessage("Stock added Successfully");

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;

	}

	// JPA TO CHECK STOCK FOR A SPECIFIED PRODUCT
	@Override
	public Response<Stocks> checkStock(long productId) {
		Response<Stocks> response = new Response<Stocks>();
		try {
			response.setStatus("Success");
			response.setData(stocksRepository.findByProductId(productId));
			response.setMessage("Stock fetched Successfully");

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;

	}

	// JPA TO check list of stocks
	@Override
	public Response<List<Stocks>> checkStock() {
		Response<List<Stocks>> response = new Response<List<Stocks>>();
		try {
			response.setStatus("Success");
			response.setData(stocksRepository.findAll());
			response.setMessage("Stock fetched Successfully");

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;

	}

	// JPA to find seller based on seller id
	@Override
	public Seller findSeller(long id) {
		try {
			return sellerRepository.findById(id);

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// JPA to fetch product based on product name and seller id
	@Override
	public Response<Products> getProduct(String productName , long sellerId) {
		Response<Products> response = new Response<Products>();
		try {
			Products product = productRepository.findByProductNameAndSellerId(productName , sellerId);
			response.setStatus("Success");
			response.setData(product);
			response.setMessage("Product fetched Successfully");

		} catch (Exception e) {
			response.setStatus("Failed");
			response.setMessage("Exception occured");
		}
		return response;
	}

	// JPA to find seller based on seller id
	@Override
	public Seller findSeller(String id) {
		try {
			return sellerRepository.findBySellerName(id);

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
