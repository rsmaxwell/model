package com.rsmaxwell.infection.model.integrate;

import com.rsmaxwell.infection.model.config.Connector;
import com.rsmaxwell.infection.model.config.Group;
import com.rsmaxwell.infection.model.model.Population;
import com.rsmaxwell.infection.model.model.Rates;

public class RungeKutta extends AbstractIntegrate {

	// The SIR deltas for ALL the populations are calculated first, before adding
	// the deltas to the SIR values
	@Override
	public Rates calculateRates(double t, double h, Population population, Population other, Group group, Connector connector) {

		double k1S = population.S.rate(t, other.S.value, other.I.value, other.R.value, group, connector);
		double k1I = population.I.rate(t, other.S.value, other.I.value, other.R.value, group, connector);
		double k1R = population.R.rate(t, other.S.value, other.I.value, other.R.value, group, connector);

		double S2 = other.S.value + h * k1S / 2.0;
		double I2 = other.I.value + h * k1I / 2.0;
		double R2 = other.R.value + h * k1R / 2.0;

		double t2 = t + h / 2.0;
		double k2S = population.S.rate(t2, S2, I2, R2, group, connector);
		double k2I = population.I.rate(t2, S2, I2, R2, group, connector);
		double k2R = population.R.rate(t2, S2, I2, R2, group, connector);

		double S3 = other.S.value + h * k2S / 2.0;
		double I3 = other.I.value + h * k2I / 2.0;
		double R3 = other.R.value + h * k2R / 2.0;

		double t3 = t + h / 2.0;
		double k3S = population.S.rate(t3, S3, I3, R3, group, connector);
		double k3I = population.I.rate(t3, S3, I3, R3, group, connector);
		double k3R = population.R.rate(t3, S3, I3, R3, group, connector);

		double S4 = other.S.value + h * k3S;
		double I4 = other.I.value + h * k3I;
		double R4 = other.R.value + h * k3R;

		double t4 = t + h;
		double k4S = population.S.rate(t4, S4, I4, R4, group, connector);
		double k4I = population.I.rate(t4, S4, I4, R4, group, connector);
		double k4R = population.R.rate(t4, S4, I4, R4, group, connector);

		double kS = (k1S + 2.0 * (k2S + k3S) + k4S) / 6.0;
		double kI = (k1I + 2.0 * (k2I + k3I) + k4I) / 6.0;
		double kR = (k1R + 2.0 * (k2R + k3R) + k4R) / 6.0;

		return new Rates(kS, kI, kR);
	}
}