package com.cogpunk.mathhammer;

import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public interface AttackProfile {
	
	public EventProbabilityProfile<Integer, Fraction> getAttacks();
	
	public int getSkill();
	
	public int getStrength();
	
	public int getArmourPenetration();
	
	public ReRoll getToHitReRoll();
	
	public ReRoll getToWoundReRoll();
	
	public int getToHitModifier();
	
	public int getToWoundModifier();
	
	public EventProbabilityProfile<Integer, Fraction> getDamageRoll();

}
