package com.cogpunk.mathhammer;

import static org.junit.Assert.assertEquals;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Test;

import com.cogpunk.math.probability.EventProbabilityProfile;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ToHitRollTest extends SingleDiceRollTest {

	@Override
	public EventProbabilityProfile<Integer, Fraction> getProfile(int target, int modifier, ReRoll reroll) {
		return new ToHitRoll(target, modifier, reroll);
	}
	
	@Test
	public void testEqualsAndHashCode() {
		
		EqualsVerifier.simple().forClass(ToHitRoll.class).verify();
		
	}
	
	@Test
	public void testGetters() {
		ToHitRoll roll = new ToHitRoll(2, 4, ReRoll.NONE);
		
		assertEquals(2, roll.getTarget());
		assertEquals(4, roll.getModifier());
		assertEquals(ReRoll.NONE, roll.getReroll());
		
	}

}
