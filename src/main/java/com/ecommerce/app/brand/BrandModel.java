package com.ecommerce.app.brand;






import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.*;


@Component
@Entity

@Table(name="Brand")
public class BrandModel
{
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int brand_id;
	private String brand_name;
	private int categoryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public BrandModel() {
		super();
		
	}


public BrandModel(int brand_id, String brand_name, int categoryId) {
	super();
	this.brand_id = brand_id;
	this.brand_name = brand_name;
	this.categoryId = categoryId;
}


public int getBrand_id() {
	return brand_id;
}


public void setBrand_id(int brand_id) {
	this.brand_id = brand_id;
}


public String getBrand_name() {
	return brand_name;
}


public void setBrand_name(String brand_name) {
	this.brand_name = brand_name;
}


public int getCategoryId() {
	return categoryId;
}


public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}


@Override
public String toString() {
	return "BrandModel [brand_id=" + brand_id + ", brand_name=" + brand_name + ", Category_id=" + categoryId + "]";
}
	
	
	
	

}
