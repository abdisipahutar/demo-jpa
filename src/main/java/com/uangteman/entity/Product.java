package com.uangteman.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tproduct")
public class Product implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Name can't be empty!")
	@Size(max=255, message="Max character 150")
	@Column(length=255, nullable=false)
	private String name;
	
	@NotBlank(message="Description can't be empty!")
	@Size(max=255, message="Max character 150")
	@Column(length=255)
	private String description;
	
	@NotNull(message="Harga can't be empty!")
	private double harga;
	
	@NotBlank(message="Category can't be empty!")
	@ManyToOne
	private Category category;

	public Product() {}

	public Product(Long id, String name, String descripton, double harga, Category category) {
		this.id = id;
		this.name = name;
		this.description = descripton;
		this.harga = harga;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descripton) {
		this.description = descripton;
	}

	public double getHarga() {
		return harga;
	}

	public void setHarga(double harga) {
		this.harga = harga;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
