package com.uangteman.dto;

public class SearchForm {
	private String name;

	public SearchForm() {}
	
	public SearchForm(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
