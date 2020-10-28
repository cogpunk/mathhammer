package com.cogpunk.mathhammer;

import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class ToHitRollTest extends SingleDiceRollTest {

	@Override
	public EventProbabilityProfile<Integer, Fraction> getProfile(int target, int modifier, ReRoll reroll) {
		return new ToHitRoll(target, modifier, reroll);
	}

	

}
