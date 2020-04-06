package com.rsmaxwell.corvid19.config.quantity;

public class Suseptible extends Quantity {

	public Suseptible(double value) {
		super(value);
	}

	@Override
	public double rate(double t, double S, double I, double R) {
		return -transmission * S * I;
	}

}