package com.ecommerce.app.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class ProductModel 
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int product_id;
	
	private String productImage;
	private String productName;
	private String productDescription;
	private long productPrice;
	private int brandId;
	private int categoryId;
	public ProductModel() {
		super();
	}
	public ProductModel(int product_id, String productImage, String productName, String productDescription,
			long productPrice, int brandId, int categoryId) {
		super();
		this.product_id = product_id;
		this.productImage = productImage;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.brandId = brandId;
		this.categoryId = categoryId;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public int getcategoryId() {
		return categoryId;
	}
	public void setcategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "ProductModel [product_id=" + product_id + ", productImage=" + productImage + ", productName="
				+ productName + ", productDescription=" + productDescription + ", productPrice=" + productPrice
				+ ", brandId=" + brandId + ", CategoryId=" + categoryId + "]";
	}
	
	
	
	
	
	
	
	

}
