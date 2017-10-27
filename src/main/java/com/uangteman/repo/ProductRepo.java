package com.uangteman.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uangteman.entity.Category;
import com.uangteman.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
	
	public List<Product> findAll();
	
	public List<Product> findByNameIgnoringCase(String name);
	
//	Query from entity, not from table in database
	@Query("select pro from Product pro where pro.name like :paramName")
	public List<Product> findByName(@Param("paramName") String paramName);
	
	@Query("select p from Product p where p.harga between :min and :max")
	public List<Product> findByPriceRange(@Param("min") double min, @Param("max") double max);

	public List<Product> findByCategory(Category category);
}
