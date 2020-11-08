package com.cogpunk.mathhammer;

import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class ToHitRoll implements EventProbabilityProfile<Integer, Fraction> {

	private SingleDiceRoll roll;

	public ToHitRoll(int target, int modifier, ReRoll reroll) {

		roll = new SingleDiceRoll(target, modifier, reroll);
	}

	@Override
	public Fraction getProbability(Integer event) {
		return roll.getProbability(event);
	}

	@Override
	public Map<Integer, Fraction> map() {
		return roll.map();
	}

	public int getTarget() {
		return roll.getTarget();
	}

	public int getModifier() {
		return roll.getModifier();
	}

	public ReRoll getReroll() {
		return roll.getReroll();
	}

	@Override
	public String toString() {
		return "ToHitRoll [roll=" + roll + "]";
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(15, 93).append(roll).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToHitRoll rhs = (ToHitRoll) obj;

		return new EqualsBuilder().append(roll, rhs.roll).isEquals();

	}

}
