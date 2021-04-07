package com.cogpunk.mathhammer;

import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.FractionOperator;
import com.cogpunk.math.IntegerOperator;
import com.cogpunk.math.probability.EventProbabilityProfile;
import com.cogpunk.math.probability.EventProbabilityProfileAdditionAggregationStrategy;
import com.cogpunk.math.probability.EventProbabilityProfileInverter;
import com.cogpunk.math.probability.VariableIntegerZeroHandler;
import com.cogpunk.math.probability.VariableProbabilityProfileAggregator;

public class AttackDamageProfileCalculator {
	
	public AttackDamageProfile calculate(AttackProfile attackProfile, DefenceProfile defenceProfile) {
		
		EventProbabilityProfile<Integer, Fraction> hitProbabilityProfile = calculateHitProbabilityProfile(attackProfile);
		EventProbabilityProfile<Integer, Fraction> woundProbabilityProfile = calculateWoundProbabilityProfile(attackProfile, defenceProfile, hitProbabilityProfile);
		EventProbabilityProfile<Integer, Fraction> unsavedProbabilityProfile = calculateUnsavedProbabilityProfile(attackProfile, defenceProfile, woundProbabilityProfile);
		EventProbabilityProfile<Integer, Fraction> damageProbabilityProfile = calculateDamageProbabilityProfile(attackProfile, unsavedProbabilityProfile);
		
		return new AttackDamageProfile (attackProfile, defenceProfile, damageProbabilityProfile);
		
	}
	
	public EventProbabilityProfile<Integer, Fraction> calculateHitProbabilityProfile(AttackProfile attackProfile) {
		ToHitRoll toHitRoll = new ToHitRoll(attackProfile.getSkill(), attackProfile.getToHitModifier(), attackProfile.getToHitReRoll());
		
		return new VariableProbabilityProfileAggregator<>(
				attackProfile.getAttacks(),
				new EventProbabilityProfileAdditionAggregationStrategy<Integer>(new IntegerOperator()),
				new FractionOperator(),
				new VariableIntegerZeroHandler<Fraction>(),
				toHitRoll
		);
	}
	
	public EventProbabilityProfile<Integer, Fraction> calculateWoundProbabilityProfile(AttackProfile attackProfile, DefenceProfile defenceProfile, EventProbabilityProfile<Integer, Fraction> hitProbabilityProfile) {
		
		ToWoundRoll toWoundRoll = new ToWoundRoll(attackProfile.getStrength(), defenceProfile.getToughness(), attackProfile.getToWoundModifier(), attackProfile.getToWoundReRoll());
		
		return new VariableProbabilityProfileAggregator<>(
				hitProbabilityProfile,
				new EventProbabilityProfileAdditionAggregationStrategy<Integer>(new IntegerOperator()),
				new FractionOperator(),
				new VariableIntegerZeroHandler<Fraction>(),
				toWoundRoll
		);
		
	}
	
	public EventProbabilityProfile<Integer, Fraction> calculateUnsavedProbabilityProfile(AttackProfile attackProfile, DefenceProfile defenceProfile, EventProbabilityProfile<Integer, Fraction> woundProbabilityProfile) {
		
		SaveRoll saveRoll = new SaveRoll(defenceProfile.getSave(), defenceProfile.getInvulnerableSave(), attackProfile.getArmourPenetration(), defenceProfile.getSaveReRoll());
		
		EventProbabilityProfile<Integer, Fraction> unsaveRoll = new EventProbabilityProfileInverter<>(saveRoll, new FractionOperator());
		
		return new VariableProbabilityProfileAggregator<>(
				woundProbabilityProfile,
				new EventProbabilityProfileAdditionAggregationStrategy<Integer>(new IntegerOperator()),
				new FractionOperator(),
				new VariableIntegerZeroHandler<Fraction>(),
				unsaveRoll
		);

	}
	
	public EventProbabilityProfile<Integer, Fraction> calculateDamageProbabilityProfile(AttackProfile attackProfile, EventProbabilityProfile<Integer, Fraction> unsavedProbabilityProfile) {
		
		
		return new VariableProbabilityProfileAggregator<>(
				unsavedProbabilityProfile,
				new EventProbabilityProfileAdditionAggregationStrategy<Integer>(new IntegerOperator()),
				new FractionOperator(),
				new VariableIntegerZeroHandler<Fraction>(),
				attackProfile.getDamageRoll()
		);
	}

}
