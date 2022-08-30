package com.ecommerce.app.product;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.app.brand.BrandModel;
import com.ecommerce.app.category.CategoryService;
import com.ecommerce.app.exception.AlreadyExistsException;
import com.ecommerce.app.exception.BadRequestException;
import com.ecommerce.app.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.app.brand.BrandRepository;
import com.ecommerce.app.category.CategoryRepository;


@Service
public class ProductService 
{
	private  static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	
	//getAllProduct
	public List<ProductModel> getAllProduct()
	{
		List<ProductModel> productList=new ArrayList<ProductModel>();
		productRepository.findAll().forEach(productList::add);
		logger.info("getAllProduct method executed successfully");
		return productList;
		
	}

	//insert product
	public ProductModel InsertProduct(ProductModel productModel)
	{
		List<ProductModel> productModels=productRepository.findAll();
		List<BrandModel> brandModels=brandRepository.findAll();
		ProductModel brandMd= new ProductModel();

		for (BrandModel b:brandModels)
		{
			if (productModel.getBrandId()==b.getBrand_id() && productModel.getcategoryId()==b.getCategoryId())
			{
				for (ProductModel p:productModels)
				if (productModel.getProductName().equals(p.getProductName())) {

					logger.error("Product already exists with name :-" + productModel.getProductName());
					throw new AlreadyExistsException("Product already exists with name :-" + productModel.getProductName());
				} else if (productModel.getProductName().isEmpty() && productModel.getProductName().equals(" ") && productModel.getProductName().contains("null")) {
					logger.error("Product Name Not be Empty");
					throw new BadRequestException("Product Name Not be Empty");
				}else if (productModel.getProductDescription().isEmpty())
				{
					logger.error("Product Description Not be Empty");
					throw new BadRequestException("Product Description Not be Empty");
				}

			}else if (productModel.getBrandId()!=b.getBrand_id())
			{
				logger.error("Brand Not Found with id :-"+productModel.getBrandId());
				throw new NotFoundException("Brand Not Found with id :-"+productModel.getBrandId());
			}else if (productModel.getcategoryId()!=b.getCategoryId()) {

				logger.error("Category Not Found with id :-"+productModel.getcategoryId());
				throw new NotFoundException("Category Not Found with id :-"+productModel.getcategoryId());
			}
		}



		brandMd=productRepository.save(productModel);
		logger.info("InsertProduct method executed successfully");
		return brandMd;
	}
		//getProductById
		public ProductModel getProductById(int id)
		{
			ProductModel pro=productRepository.findById(id).get();

			logger.info("getProductById method executed successfully");
			return pro;
		}
		
		
		//deleteProductById
		public void deleteProductById(int id) {
			logger.info("Product deleted..");
			productRepository.deleteById(id);
			logger.info("getProductById method executed successfully");
			
		}
}
