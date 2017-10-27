package com.uangteman.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uangteman.repo.ProductRepo;
import com.uangteman.dto.PriceForm;
import com.uangteman.dto.Result;
import com.uangteman.dto.SearchForm;
import com.uangteman.entity.Category;
import com.uangteman.entity.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductRepo repo;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Product> findAll(){
		return repo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@Valid @RequestBody Product product, Errors errors) {
		// Code Valdasi dibawah dijlanakn pada saat membuat object entity user dari request body
		Result result = new Result<>();
		if(errors.hasErrors()) {
			for(ObjectError err: errors.getAllErrors()) {
				result.getMessage().add(err.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(result);
		}
		Product output = repo.save(product);
		result.setPayload(output);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/search")
	public List<Product> findByName(@RequestBody SearchForm form){
		return repo.findByNameIgnoringCase(form.getName());
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/like")
	public List<Product> findByNameLike(@RequestBody SearchForm form){
		return repo.findByName("%"+form.getName()+"%");
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/price-range")
	public List<Product> findByPriceRange(@RequestBody PriceForm form){
		return repo.findByPriceRange(form.getMin(), form.getMax());
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/byCategory")
	public List<Product> findByCategory(@RequestBody Category category){
		return repo.findByCategory(category);
	}
}
