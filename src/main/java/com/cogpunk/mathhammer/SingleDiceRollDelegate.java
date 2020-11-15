package com.cogpunk.mathhammer;

import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public abstract class SingleDiceRollDelegate implements EventProbabilityProfile<Integer, Fraction> {
	
	protected SingleDiceRoll roll;
	
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SingleDiceRollDelegate)) {
			return false;
		}
		SingleDiceRollDelegate castOther = (SingleDiceRollDelegate) other;
		return new EqualsBuilder().append(roll, castOther.roll).isEquals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(roll).toHashCode();
	}

}
