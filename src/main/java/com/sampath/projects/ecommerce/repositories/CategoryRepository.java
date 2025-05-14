package com.sampath.projects.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sampath.projects.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findByCategoryName(String categoryName);

}
