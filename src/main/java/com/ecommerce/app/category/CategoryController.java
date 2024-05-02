package com.ecommerce.app.category;

import java.util.List;


import com.ecommerce.app.ECommerceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class CategoryController
{
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	CategoryService service;
	
	@Autowired
	CategoryRepository categoryRepository;


	@PostMapping("/addCategory")
	public CategoryModel addCategory(@RequestBody CategoryModel Cat)
	{
		logger.info("addCategory API HIT");

		logger.info("Demo log");
		return service.insertCategory(Cat);


	}
	
	@GetMapping("/getAllCategory")
	public List<CategoryModel> getAllCategory()
	{
		logger.info("getAllCategory API HIT");
		return service.getAllCategory();
		
	}
	
	@GetMapping("/getCategoryWithBrand")
	public List<Object> getCategoryWithBrand()
	{
		logger.info("getCategoryWithBrand API HIT");
		return service.getCategoryWithBrand();
	}
	
	@GetMapping("/getCategoryById/{category_id}")
	public List<Object> getCategoryById(@PathVariable("category_id")int id)
	{
		List<Object>list=service.getCategoryById(id);

		logger.info("getCategoryById API HIT");
		return list;
		
	}
	
	@GetMapping("/getCategoryByName/{category_name}")
	public List<Object> getCategoryByName(@PathVariable("category_name")String category_name)
	{
		List<Object>list=service.getCategoryByName(category_name);
		logger.info("getCategoryByName API HIT");
		return list;
	}

	@DeleteMapping("/deleteCategoryById/{category_id}")
	public void deleteCategoryById(@PathVariable("category_id")int id)
	{
		logger.info("deleteCategoryById API HIT");
		service.deleteCategoryById(id);
	}

}
