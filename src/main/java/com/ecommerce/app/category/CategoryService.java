package com.ecommerce.app.category;

import java.util.ArrayList;
import java.util.List;


import com.ecommerce.app.exception.AlreadyExistsException;
import com.ecommerce.app.exception.BadRequestException;
import com.ecommerce.app.exception.NotFoundException;
import com.ecommerce.app.exception.OkException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.brand.BrandModel;
import com.ecommerce.app.brand.BrandRepository;
import com.ecommerce.app.product.ProductModel;
import com.ecommerce.app.product.ProductRepository;



@Service
public class CategoryService 
{
	private  static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public List<CategoryModel> getAllCategory() {
		
		List<CategoryModel> cat =new ArrayList<CategoryModel>();
		categoryRepository.findAll().forEach(cat::add);
		logger.info("getAllCategory method executed successfully");

		logger.info("asd");

		return cat;
	}
	
	//insert category
	public CategoryModel insertCategory(CategoryModel categoryModel)
	{

		List<CategoryModel>modelList=categoryRepository.findAll();
		CategoryModel categoryModel1= new CategoryModel();

		for (CategoryModel c:modelList)
		{

			if (c.getCategory_name().equals(categoryModel.getCategory_name()))
			{
				logger.error("Category already exists with name :- "+categoryModel.getCategory_name());

				throw new AlreadyExistsException("Category already exists with name :- "+categoryModel.getCategory_name());


			}else if (categoryModel.getCategory_name().isEmpty())
			{
				logger.error("Category Name Not be Empty");

				throw new BadRequestException("Category Name Not be Empty");

			}else if(categoryModel.getCategory_name().equals(" ") && categoryModel.getCategory_name().equals("  "))
			{
				logger.error("Category Name Not be Space");
			throw new BadRequestException("Category Name Not be Space");
			}else if (categoryModel.getCategory_name().contains("null"))
			{
				logger.error("Category Name Not be null");

			throw new BadRequestException("Category Name Not be null");
			}
		}
		categoryModel1 =categoryRepository.save(categoryModel);


		return categoryModel1;

	}
	
	public List<Object> getCategoryWithBrand()
	{

			List<BrandModel> bList = new ArrayList<BrandModel>();
			brandRepository.findAll().forEach(bList::add);
			
			
			List<CategoryModel>cList= new ArrayList<CategoryModel>();
			categoryRepository.findAll().forEach(cList::add);
			
			List<ProductModel>pList= new ArrayList<ProductModel>();
			productRepository.findAll().forEach(pList::add);
		
			List<Object> merge = new ArrayList<>();
			merge.addAll(cList);
			merge.addAll(bList);
			merge.addAll(pList);
		return merge;
	}
	
	//getCategoryById
	public List<Object> getCategoryById(int id) {
		
		List<Object> mylist= new ArrayList<>();


		CategoryModel categoryModel=categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Category Not Exist With Id :- "+id));

		mylist.add(categoryModel);
		List<BrandModel>bList= brandRepository.findAll();
		List<ProductModel>pList=productRepository.findAll();
		for(BrandModel b:bList)
		{
			if(b.getCategoryId()==id)
			{
				mylist.add(b);
			for(ProductModel p:pList)
			{
					if(p.getBrandId()==b.getBrand_id())
					{
						mylist.add(p);
					}
			}
			}
		}
			if (mylist==null)
			{
				logger.error("getCategoryById method hit and having list null");
			}

			return mylist;
	}

	//getCategoryByName
	public List<Object> getCategoryByName(String category_name) 
	{
		List<Object> list= new ArrayList<>();
		
		List<CategoryModel>cList=categoryRepository.findAll();
		List<BrandModel>bList=brandRepository.findAll();
		List<ProductModel>pList=productRepository.findAll();

		
		for(CategoryModel c:cList)
		{
			if(c.getCategory_name().equals(category_name))
			{
				list.add(c);
				for(BrandModel b:bList)
				{
					if(c.getCategory_id()==b.getCategoryId())
					{
						list.add(b);
						for(ProductModel p:pList)
						{
							if(b.getBrand_id()==p.getBrandId())
							{
								list.add(p);
							}
						}
					}
				}
			}
		}

		if (list.isEmpty())
		{
			logger.error("Category Not Exist With Name :- "+category_name);
			throw new NotFoundException("Category Not Exist With Name :- "+category_name);

		}
	
		return list;
	}

	//To delete categoryByid With brand and product
	public void deleteCategoryById(int id)
	{
		//categoryRepository.deleteById(id);

		List<CategoryModel>modelList=categoryRepository.findAll();
		List<BrandModel>brandModelsList=brandRepository.findAll();
		List<ProductModel>productModels=productRepository.findAll();

		for (CategoryModel c:modelList)
		{

			if (c.getCategory_id()==id)
			{
				logger.info("Category id finding in CategoryModel ");
				for (BrandModel b:brandModelsList)
				{
					if (b.getCategoryId()==id)
					{
						logger.info("Category id finding in BrandModel ");
						for (ProductModel p:productModels)
						{
							if (p.getcategoryId()==id)
							{
								logger.info("Category id finding in ProductModel ");
								productRepository.deleteById(p.getProduct_id());
								brandRepository.deleteById(b.getBrand_id());
								categoryRepository.deleteById(id);

							}
						}
					}
				}
			}
		}

		logger.info("Category deleted by ID with Category Brand & Product..");
		throw new OkException("The request was fulfilled.");

	}
}
