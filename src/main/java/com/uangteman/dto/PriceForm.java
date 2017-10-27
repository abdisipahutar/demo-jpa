package com.uangteman.dto;

public class PriceForm {
	private double min;
	private double max;
	
	public PriceForm() {}
	
	public PriceForm(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}
}
