package com.sampath.projects.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sampath.projects.ecommerce.config.AppConstants;
import com.sampath.projects.ecommerce.payload.CategoryDTO;
import com.sampath.projects.ecommerce.payload.CategoryResponse;
import com.sampath.projects.ecommerce.service.CategoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
//	@GetMapping("/public/categories")
//	public List<Category> getAllCategories(){
//		return categoryService.getAllCategories();
//	}
	
//	@PostMapping("/public/categories")
//	public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
//		categoryService.createCategory(category);
//		return new ResponseEntity<>("category created successfully",HttpStatus.OK);
//		
//	}
	
	
	@DeleteMapping("/public/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> deleteCategory( @PathVariable Long categoryId) {
		CategoryDTO deletedCategoryDTO = categoryService.deleteCategory(categoryId);
		return new ResponseEntity<CategoryDTO>(deletedCategoryDTO,HttpStatus.OK);
			
	}
	
	@PutMapping("/public/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable long categoryId, @Valid @RequestBody CategoryDTO categoryDTO) {
		
		CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryId,categoryDTO);
		
		return new ResponseEntity<CategoryDTO>(savedCategoryDTO,HttpStatus.OK);
	}
	
//	After using DTOS 
	
	@GetMapping("/public/categories")
	public CategoryResponse getAllCategories(@RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
											@RequestParam(defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
											@RequestParam(defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
											@RequestParam(defaultValue = AppConstants.SORT_ORDER,required = false) String sortOrder){
		return categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
		 
	}
	@PostMapping("/public/categories")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO createdCategoryDTO = categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(createdCategoryDTO,HttpStatus.CREATED);
		
	}
	
}
