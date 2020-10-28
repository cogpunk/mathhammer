package com.cogpunk.mathhammer;

import java.util.Map;

import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class SaveRoll implements EventProbabilityProfile<Integer, Fraction> {
	
	private int target;
	
	private Integer invulnerabaleTarget;
	
	private int modifier;
	
	private ReRoll reroll;
	
	private SingleDiceRoll roll;
	
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

	@Override
	public Fraction getProbability(Integer event) {
		return roll.getProbability(event);
	}

	@Override
	public Map<Integer, Fraction> map() {
		return roll.map();
	}

	@Override
	public String toString() {
		return "SaveRoll [target=" + target + ", invulnerabaleTarget=" + invulnerabaleTarget + ", modifier=" + modifier
				+ ", reroll=" + reroll + ", roll=" + roll + "]";
	}
	
	

}
