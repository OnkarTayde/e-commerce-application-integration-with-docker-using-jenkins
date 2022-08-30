package com.ecommerce.app.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;



@Component
@Entity
@Table(name="Category")
public class CategoryModel 
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int category_id;

//	@NotNull(message = "category_name should not br null")
	private String category_name;

	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name ="category_id",referencedColumnName ="category_id") //it is used to define F-key
//	List<BrandModel> brandlist= new ArrayList<BrandModel>();
	

	


	public CategoryModel() {
		super();
		
	}


	public CategoryModel(int category_id, String category_name) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		
	}


	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public String getCategory_name() {
		return category_name;
	}


	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}


	@Override
	public String toString() {
		return "CategoryModel [category_id=" + category_id + ", category_name=" + category_name + "]";
	}



	
	
	
	
	
}
