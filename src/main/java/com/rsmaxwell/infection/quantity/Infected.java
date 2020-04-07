package com.rsmaxwell.infection.quantity;

import com.rsmaxwell.infection.config.Connector;
import com.rsmaxwell.infection.config.Group;

public class Infected extends Quantity {

	public Infected(double value) {
		super(value);
	}

	@Override
	public double rate(double t, double S, double I, double R, Group group, Connector connector) {
		return connector.transmission * S * I - group.recovery * I;
	}

}
