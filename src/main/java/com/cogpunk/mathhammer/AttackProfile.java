package com.cogpunk.mathhammer;

import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public interface AttackProfile {
	
	EventProbabilityProfile<Integer, Fraction> getAttacks();
	
	int getSkill();
	
	int getStrength();
	
	int getArmourPenetration();
	
	ReRoll getToHitReRoll();
	
	ReRoll getToWoundReRoll();
	
	int getToHitModifier();
	
	int getToWoundModifier();
	
	EventProbabilityProfile<Integer, Fraction> getDamageRoll();

}
