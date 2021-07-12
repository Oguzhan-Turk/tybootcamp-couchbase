package com.tybootcamp.couchbase.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tybootcamp.couchbase.domain.Product;
import com.tybootcamp.couchbase.domain.Seller;
import com.tybootcamp.couchbase.repository.SellerRepository;

@Service
public class SellerService {

	private final SellerRepository sellerRepository;

	public SellerService(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}

	public Seller create(String name) {
		Seller seller = new Seller(name);
		return sellerRepository.save(seller);
	}

	public Seller findById(String id) {
		return sellerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(String.format("Seller not found with id: %s", id)));
	}

	public Seller findByName(String name) {
		return sellerRepository.findById(name)
				.orElseThrow(() -> new RuntimeException(String.format("Seller not found with name: %s", name)));
	}

	public void addProductsToSeller(String sellerName, List<Product> products) {
		Seller seller = this.findByName(sellerName);
		seller.setProducts(products);
		sellerRepository.save(seller);
	}

	public List<Product> getProductsByCategory(String sellerName, String category) {
		Seller seller = this.findByName(sellerName);
		List<Product> products = seller.getProducts();
		return products.stream().filter(product -> product.getCategory().contains(category))
				.collect(Collectors.toList());
	}
}

