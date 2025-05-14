package com.sampath.projects.ecommerce.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sampath.projects.ecommerce.exceptions.APIException;
import com.sampath.projects.ecommerce.exceptions.ResourceNotFoundException;
import com.sampath.projects.ecommerce.model.Category;
import com.sampath.projects.ecommerce.payload.CategoryDTO;
import com.sampath.projects.ecommerce.payload.CategoryResponse;
import com.sampath.projects.ecommerce.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	
//	@Override
//	public List<Category> getAllCategories() {
//		
//		List<Category> categories = categoryRepository.findAll();
//		if(categories.isEmpty())
//			throw new APIException(" No Category exists !!!");
//		return categories;
//	}

//	@Override
//	public void deleteCategory(Long categoryId) {
//		Category category1 = categoryRepository.findById(categoryId)
//									.orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
//		categoryRepository.delete(category1);
//	}

//	@Override
//	public void createCategory(Category category) {
//		Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
//		if(savedCategory != null)
//			throw new APIException("Category with category name \" "+ category.getCategoryName()+"\" already exists !!!!");
//		categoryRepository.save(category);
//	}

//	@Override
//	public void updateCategory(long categoryId, Category category) {
//		Category savedCategory = categoryRepository.findById(categoryId)
//								.orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
//		category.setCategoryId(savedCategory.getCategoryId());
//		categoryRepository.save(category);
//								
//	}

	
	
	@Override
	public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
		Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
								? Sort.by(sortBy).ascending()
								: Sort.by(sortBy).descending();
								
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sortByAndOrder);
		Page<Category> pageDetails = categoryRepository.findAll(pageable);
		List<Category> categories = pageDetails.getContent();
		if(categories.isEmpty())
			throw new APIException(" No Category exists !!!");
		List<CategoryDTO> categoryDTOs = categories.stream()
													.map(category -> modelMapper.map(category, CategoryDTO.class))
													.toList();
		CategoryResponse categoryPageDetails = new CategoryResponse();
		categoryPageDetails.setContent(categoryDTOs);
		categoryPageDetails.setIsLast(pageDetails.isLast());
		categoryPageDetails.setPageNumber(pageDetails.getNumber());
		categoryPageDetails.setPageSize(pageDetails.getSize());
		categoryPageDetails.setTotalElements(pageDetails.getTotalElements());
		categoryPageDetails.setTotalPages(pageDetails.getTotalPages());
		return categoryPageDetails;
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
		if(savedCategory != null)
			throw new APIException("Category with category name \" "+ category.getCategoryName()+"\" already exists !!!!");
	
		Category savedCategoryOb = categoryRepository.save(category);
		return modelMapper.map(savedCategoryOb, CategoryDTO.class);
	}
	
	@Override
	public CategoryDTO updateCategory(long categoryId, CategoryDTO categoryDTO) {
		Category savedCategory = categoryRepository.findById(categoryId)
								.orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		Category mappedcategory = modelMapper.map(categoryDTO, Category.class);
		mappedcategory.setCategoryId(categoryId);
		return modelMapper.map(categoryRepository.save(mappedcategory), CategoryDTO.class);
	}
	
	@Override
	public CategoryDTO deleteCategory(long categoryId) {
		Category category1 = categoryRepository.findById(categoryId)
									.orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		CategoryDTO deletedCategoryDTO = modelMapper.map(category1, CategoryDTO.class);
		categoryRepository.delete(category1);
		return deletedCategoryDTO;
	}
	
	
	
	

}
