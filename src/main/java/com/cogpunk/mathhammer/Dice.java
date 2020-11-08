package com.cogpunk.mathhammer;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.FractionOperator;
import com.cogpunk.math.probability.ComparableEventProbabilityProfile;
import com.cogpunk.math.probability.ComparableEventProbabilityProfileImpl;

public class Dice implements ComparableEventProbabilityProfile<Integer, Fraction> {
	
	private ComparableEventProbabilityProfile<Integer, Fraction> probabilityProfile;
	
	public Dice(int sides) {
		
		Fraction prob = new Fraction(1, sides);
		
		Map<Integer, Fraction> map = new TreeMap<Integer, Fraction>();
		
		for (int n= 1; n <= sides; n++ ) {
			map.put(n,  prob);
		}
		
		probabilityProfile = new ComparableEventProbabilityProfileImpl<Integer, Fraction>(map, new FractionOperator());
		
		
	}

	@Override
	public Fraction getProbability(Integer result) {
		return probabilityProfile.getProbability(result);
	}

	@Override
	public Fraction getProbabilityGreaterThanOrEqualTo(Integer target) {
		return probabilityProfile.getProbabilityGreaterThanOrEqualTo(target);
	}

	@Override
	public Fraction getProbabilityLessThanOrEqualTo(Integer target) {
		return probabilityProfile.getProbabilityLessThanOrEqualTo(target);
	}

	@Override
	public Fraction getProbabilityGreaterThan(Integer target) {
		return probabilityProfile.getProbabilityGreaterThan(target);
	}

	@Override
	public Fraction getProbabilityLessThan(Integer target) {
		return probabilityProfile.getProbabilityLessThan(target);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(15, 93).
			       append(probabilityProfile).
			       toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dice rhs = (Dice) obj;
		
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(probabilityProfile, rhs.probabilityProfile)
				.isEquals();
		
		
	}

	@Override
	public Map<Integer, Fraction> map() {
		return probabilityProfile.map();
	}

	@Override
	public String toString() {
		return "Dice [probabilityProfile=" + probabilityProfile + "]";
	}

	
	
	
	
	

}
