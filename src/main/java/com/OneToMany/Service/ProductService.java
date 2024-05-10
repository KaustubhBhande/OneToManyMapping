package com.OneToMany.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OneToMany.Model.Product;
import com.OneToMany.Repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> GetAllProducts() {
		List<Product> list = (List<Product>) this.productRepository.findAll();
		return list;
	}

	public Optional<Product> GetProduct(Integer id) {
		return this.productRepository.findById(id);
	}

	public Product AddProduct(Product product) {
		return this.productRepository.save(product);
	}

	public boolean DeleteProduct(Integer id) {
		boolean exists = productRepository.existsById(id);
		if (exists) {
			productRepository.deleteById(id);
			return true;
		} else {

			return false;
		}
	}

	public Product UpdateProduct(Integer id, Product product) {
		Product existingproduct = productRepository.findById(id).orElse(null);
		existingproduct.setId(product.getId());
		existingproduct.setName(product.getName());
		existingproduct.setDescription(product.getDescription());
		existingproduct.setPrice(product.getPrice());
		return productRepository.save(existingproduct);
	}
}
