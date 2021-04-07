package com.cogpunk.mathhammer;

import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class SaveRoll implements EventProbabilityProfile<Integer, Fraction> {
	
	private final int target;
	
	private final Integer invulnerabaleTarget;
	
	private final int modifier;
	
	private final ReRoll reroll;
	
	private final SingleDiceRoll roll;
	
	public SaveRoll(int target, Integer invulnerabaleTarget, int modifier, ReRoll reroll) {
		
		this.target = target;
		this.invulnerabaleTarget = invulnerabaleTarget;
		this.modifier = modifier;
		this.reroll = reroll;
		
		// use the best of the modified targgt and the invulnerable target
		
		int modifiedTarget = target - modifier;
		
		if (invulnerabaleTarget== null || modifiedTarget < invulnerabaleTarget) {
			roll = new SingleDiceRoll(target, modifier, reroll);
		} else {
			// invulnerables aren't modified
			roll = new SingleDiceRoll(invulnerabaleTarget, 0, reroll);
		}
	}
	
	public int getTarget() {
		return target;
	}

	public Integer getInvulnerabaleTarget() {
		return invulnerabaleTarget;
	}

	public int getModifier() {
		return modifier;
	}

	public ReRoll getReroll() {
		return reroll;
	}

	public Fraction getProbability(Integer event) {
		return roll.getProbability(event);
	}

	public Map<Integer, Fraction> map() {
		return roll.map();
	}

	@Override
	public String toString() {
		return "SaveRoll [target=" + target + ", invulnerabaleTarget=" + invulnerabaleTarget + ", modifier=" + modifier
				+ ", reroll=" + reroll + ", roll=" + roll + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SaveRoll)) {
			return false;
		}
		SaveRoll castOther = (SaveRoll) other;
		return new EqualsBuilder().append(target, castOther.target)
				.append(invulnerabaleTarget, castOther.invulnerabaleTarget).append(modifier, castOther.modifier)
				.append(reroll, castOther.reroll).append(roll, castOther.roll).isEquals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(target).append(invulnerabaleTarget).append(modifier).append(reroll)
				.append(roll).toHashCode();
	}
	
	
	

}
