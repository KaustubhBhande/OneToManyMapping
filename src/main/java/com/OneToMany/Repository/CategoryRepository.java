package com.OneToMany.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.OneToMany.Model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	public Category findById(int id);
}
