package com.cogpunk.mathhammer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;
import com.cogpunk.math.probability.SimpleProbabilityProfileImpl;

public class FixedValue implements EventProbabilityProfile<Integer, Fraction> {

	private EventProbabilityProfile<Integer, Fraction> profile;

	public FixedValue(int value) {
		super();

		Map<Integer, Fraction> map = new HashMap<Integer, Fraction>();
		map.put(value, Fraction.ONE);
		profile = new SimpleProbabilityProfileImpl<Integer, Fraction>(map);

	}

	@Override
	public Fraction getProbability(Integer event) {
		return profile.getProbability(event);
	}

	@Override
	public Map<Integer, Fraction> map() {
		return profile.map();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(15, 93).append(profile).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FixedValue rhs = (FixedValue) obj;

		return new EqualsBuilder().append(profile, rhs.profile).isEquals();

	}

}
