package com.OneToMany.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.OneToMany.Model.Product;
import com.OneToMany.Service.ProductService;

@RestController
@RequestMapping("/Product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("")
	public ResponseEntity<?> getStudent() {

		List<Product> product = productService.GetAllProducts();
		if (product.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
		else {
		return ResponseEntity.ok(product);
		}
	}

	@GetMapping("/{id}")

	public ResponseEntity<?> getStudentById(@PathVariable Integer id) {

		Optional<Product> productById = productService.GetProduct(id);
		if (productById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product By Id Not Found");

		} else {
			Product product = productById.get();

			return ResponseEntity.ok().body(product);
		}
	}

	@PostMapping(" ")
	public ResponseEntity<?> AddProduct(@RequestBody Product product) {
		productService.AddProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product Added Sucessfully.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> DeleteProduct(@PathVariable Integer id) {

		boolean deleted = productService.DeleteProduct(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product with ID " + id + " Deleted Sucessfully.");
		}

		else

		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + " Not Found.");

		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {

		Optional<Product> existingproduct = productService.GetProduct(id);
		if (!existingproduct.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + "Not Found.");
		}

		productService.UpdateProduct(id, product);
		return ResponseEntity.ok().body("Product with ID " + id + " Updated Sucessfully.");
	}
}
