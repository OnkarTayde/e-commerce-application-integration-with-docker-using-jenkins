package com.ecommerce.app.product;

import java.util.List;

import com.ecommerce.app.category.CategoryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController 
{
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);


	@Autowired
	ProductService productService;
	
	@PostMapping("/addProduct")
	public ProductModel addProduct(@RequestBody ProductModel productModel)
	{
		logger.info("addProduct API HIT");
		return productService.InsertProduct(productModel);

	}
	
	@GetMapping("/getAllProducts")
	public List<ProductModel> getAllProduct()
	{
		List<ProductModel>productlist=productService.getAllProduct();
		logger.info("getAllProducts API HIT");
		return productlist;
	}
	
	@GetMapping("/getProductById/{product_id}")
	public ProductModel getProductById(@PathVariable("product_id")int id)
	{
		ProductModel product =productService.getProductById(id);
		logger.info("getProductById API HIT");
		return product;
		
	}
	
	@DeleteMapping("/deleteProductById/{product_id}")
	public void deleteProductById(@PathVariable("product_id")int id)
	{
		logger.info("deleteProductById API HIT");
		productService.deleteProductById(id);
	}
	
}
