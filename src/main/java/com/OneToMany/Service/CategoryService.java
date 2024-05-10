package com.OneToMany.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OneToMany.Model.Category;
import com.OneToMany.Repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> GetAllCategory() {
	    return  (List<Category>) this.categoryRepository.findAll();
	 
	}

	public Optional<Category> GetCategory(Integer id) {
		return this.categoryRepository.findById(id);
	}

	public Category AddCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	public boolean DeleteCategory(Integer id) {
		boolean exists = categoryRepository.existsById(id);
		if (exists) {
			categoryRepository.deleteById(id);
			return true;
		} else {

			return false;
		}
	}

	public Category UpdateCategory(Integer id, Category category) {
		Category existingcategory = categoryRepository.findById(id).orElse(null);
		existingcategory.setName(category.getName());
		return categoryRepository.save(existingcategory);
	}
}