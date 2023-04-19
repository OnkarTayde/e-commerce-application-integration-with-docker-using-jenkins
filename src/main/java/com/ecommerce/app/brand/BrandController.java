package com.ecommerce.app.brand;

import java.util.List;

import com.ecommerce.app.category.CategoryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class BrandController {


	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	BrandService brandService;
	
	@PostMapping(value="/addBrand")
	public BrandModel insertIntoTable(@RequestBody BrandModel b)
	{
		logger.info("addBrand API hit");
		BrandModel  brandModel =brandService.InsertBrand(b);
		return brandModel;
		
	}
	
	@GetMapping(value="/getAllBrands")
	public List<BrandModel> getAllBrands()
	{
		logger.info("getAllBrands API HIT");
		return brandService.getAllBrand();
		
	}

	@GetMapping("/getBrandByName/{Brand_name}")
	public List<Object> getBrandByName(@PathVariable("Brand_name")String Brand_name)
	{
		List<Object>list=brandService.getBrandByName(Brand_name);
		logger.info("getBrandByName API HIT");
		return list;
	}

	@DeleteMapping("/deleteBrandById/{brand_id}")
	public void deleteBrandById(@PathVariable("brand_id")int id)
	{
		logger.info("deleteBrandById API HIT");

		brandService.deleteBrandById(id);
	}
}
