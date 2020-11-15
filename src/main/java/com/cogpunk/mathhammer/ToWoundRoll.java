package com.cogpunk.mathhammer;

import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class ToWoundRoll implements EventProbabilityProfile<Integer, Fraction> {
	
	private SingleDiceRoll roll;
	
	public ToWoundRoll(int strength, int toughness, int modifier, ReRoll reroll) {
		
		super();
		
		roll = new SingleDiceRoll(getTarget(strength, toughness), modifier, reroll);
		
		
	}
	
	private int getTarget(int strength, int toughness) {
		
		int target = 0;
		
		if (strength >= 2*toughness) {
			target = 2;
		} else if (strength > toughness) {
			target = 3;
		} else if (strength == toughness) {
			target = 4;
		} else if (2*strength <= toughness) {
			target = 6;
		} else if (strength < toughness) {
			target = 5;
		} 
		
		return target;
		
	}

	public ToWoundRoll(int target, int modifier, ReRoll reroll) {
		
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ToWoundRoll)) {
			return false;
		}
		ToWoundRoll castOther = (ToWoundRoll) other;
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
