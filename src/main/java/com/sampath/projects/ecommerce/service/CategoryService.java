package com.sampath.projects.ecommerce.service;

import com.sampath.projects.ecommerce.payload.CategoryDTO;
import com.sampath.projects.ecommerce.payload.CategoryResponse;
public interface CategoryService {
	
	CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	CategoryDTO deleteCategory(long categoryId);
	CategoryDTO updateCategory(long categoryId, CategoryDTO categoryDTO);
	

}
