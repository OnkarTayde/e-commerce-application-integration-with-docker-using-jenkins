package com.ecommerce.app.brand;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.app.category.CategoryModel;
import com.ecommerce.app.category.CategoryService;
import com.ecommerce.app.exception.AlreadyExistsException;
import com.ecommerce.app.exception.BadRequestException;
import com.ecommerce.app.exception.NotFoundException;
import com.ecommerce.app.exception.OkException;
import com.ecommerce.app.product.ProductModel;
import com.ecommerce.app.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.app.category.CategoryRepository;

@Service
public class BrandService 
{
	private  static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;
	
	
	//InsertBrand
	public BrandModel InsertBrand(BrandModel brandModel)
	{
		List<BrandModel> brandModels=brandRepository.findAll();

		BrandModel brandModeA = new BrandModel();

		for (BrandModel model:brandModels) {
			if (model.getBrand_name().equals(brandModel.getBrand_name())) {

				logger.error("Brand already exists with name :-" + brandModel.getBrand_name());
				throw new AlreadyExistsException("Brand already exists with name :-" + brandModel.getBrand_name());
			} else if (brandModel.getBrand_name().isEmpty()) {
				logger.error("Brand Name Not be Empty");
				throw new BadRequestException("Brand Name Not be Empty");

			} else if (brandModel.getBrand_name().equals(" ")) {

				logger.error("Brand Name Not be Space");
				throw new BadRequestException("Brand Name Not be Space");
			} else if (brandModel.getBrand_name().contains("null")) {

				logger.error("Brand Name Not be null");
				throw new BadRequestException("Brand Name Not be null");
			}
		}
		brandModeA=brandRepository.save(brandModel);

		logger.info("InsertBrand method executed successfully");
		return brandModeA;
	}


	//getAllBrand
	public List<BrandModel> getAllBrand() {
		
		
		List<BrandModel> brandlist =new ArrayList<BrandModel>();
		brandRepository.findAll().forEach(brandlist::add);
		logger.info("getAllBrand method executed successfully");
		
		return brandlist;
		
		
	}

	//get Brand by name
	public List<Object> getBrandByName(String brand_name)
	{
		List<Object> list= new ArrayList<>();


		List<BrandModel>bList=brandRepository.findAll();
		List<ProductModel>pList=productRepository.findAll();

		for (BrandModel b:bList)
		{
			if (b.getBrand_name().equals(brand_name))
			{
				list.add(b);
				for (ProductModel p:pList)
				{
					if (b.getBrand_id()==p.getBrandId())
					{
						list.add(p);
					}
				}
			}
		}
		if (list.isEmpty())
		{
			logger.error("Brand Not Exist With Name :- "+brand_name);
			throw new NotFoundException("Brand Not Exist With Name :- "+brand_name);
		}
		return list;
	}

	//To delete brandById With  product
	public void deleteBrandById(int id)
	{
		List<BrandModel>brandModelsList=brandRepository.findAll();
		List<ProductModel>productModelList=productRepository.findAll();

		for (BrandModel b:brandModelsList)
		{
			if (b.getBrand_id()==id)
			{
				logger.info("Category id finding in BrandModel ");
				for (ProductModel p:productModelList)
				{
					if (p.getBrandId()== b.getBrand_id())
					{
						logger.info("Category id finding in ProductModel ");
						productRepository.deleteById(p.getProduct_id());
						brandRepository.deleteById(b.getBrand_id());
					}
				}
			}
		}
		logger.info("Brand deleted By Id with Product..");
		throw new OkException("The request was fulfilled.");

	}

}
