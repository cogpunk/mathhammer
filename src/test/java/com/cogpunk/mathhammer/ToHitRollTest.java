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
		ToHitRoll toHitRule = new ToHitRoll(2, 4, ReRoll.NONE);
		
		assertEquals(2, toHitRule.getTarget());
		assertEquals(4, toHitRule.getModifier());
		assertEquals(ReRoll.NONE, toHitRule.getReroll());
		
	}

}
