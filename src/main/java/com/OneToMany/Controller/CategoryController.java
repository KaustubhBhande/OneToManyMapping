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
import com.OneToMany.Model.Category;
import com.OneToMany.Service.CategoryService;

@RestController
@RequestMapping("/Category")
public class CategoryController {
	@Autowired
	private CategoryService CategoryService;

	@GetMapping("")
	public ResponseEntity<?> getStudent() {

		List<Category> category = CategoryService.GetAllCategory();
		if (category.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		}
		return ResponseEntity.ok(category);
	}

	@GetMapping("/{id}")

	public ResponseEntity<?> getStudentById(@PathVariable Integer id) {

		Optional<Category> CategoryById = CategoryService.GetCategory(id);
		if (CategoryById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category By Id Not Found");

		} else {
			Category Category = CategoryById.get();

			return ResponseEntity.ok().body(Category);
		}
	}

	@PostMapping(" ")
	public ResponseEntity<?> AddCategory(@RequestBody Category Category) {
		List<String> errors = CategoryService.validate(Category);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}
		
		CategoryService.AddCategory(Category);
		return ResponseEntity.status(HttpStatus.CREATED).body("Category Added Sucessfully.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> DeleteCategory(@PathVariable Integer id) {

		boolean deleted = CategoryService.DeleteCategory(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body("Category with ID " + id + " Deleted Sucessfully.");
		}

		else

		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with ID " + id + " Not Found.");

		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Category Category) {
		
		List<String> errors = CategoryService.validate(Category);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}

		Optional<Category> existingCategory = CategoryService.GetCategory(id);
		if (!existingCategory.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with ID " + id + "Not Found.");
		}

		CategoryService.UpdateCategory(id, Category);
		return ResponseEntity.ok().body("Category with ID " + id + " Updated Sucessfully.");
	}
}
