package com.cogpunk.mathhammer;

import static org.junit.Assert.assertEquals;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Test;

import com.cogpunk.math.probability.EventProbabilityProfile;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ToWoundRollTest extends SingleDiceRollTest {
	
	@Override
	public EventProbabilityProfile<Integer, Fraction> getProfile(int target, int modifier, ReRoll reroll) {
		return new ToWoundRoll(target, modifier, reroll);
	}
	
	@Test
	public void testTargetCalculation() {
		
		assertEquals(6, new ToWoundRoll(1,4,0,ReRoll.NONE).getTarget());
		assertEquals(6, new ToWoundRoll(2,4,0,ReRoll.NONE).getTarget());
		assertEquals(5, new ToWoundRoll(3,4,0,ReRoll.NONE).getTarget());
		assertEquals(4, new ToWoundRoll(4,4,0,ReRoll.NONE).getTarget());
		assertEquals(3, new ToWoundRoll(5,4,0,ReRoll.NONE).getTarget());
		assertEquals(3, new ToWoundRoll(6,4,0,ReRoll.NONE).getTarget());
		assertEquals(3, new ToWoundRoll(7,4,0,ReRoll.NONE).getTarget());
		assertEquals(2, new ToWoundRoll(8,4,0,ReRoll.NONE).getTarget());
		assertEquals(2, new ToWoundRoll(9,4,0,ReRoll.NONE).getTarget());
		assertEquals(2, new ToWoundRoll(10,4,0,ReRoll.NONE).getTarget());
		
	}
	
	@Test
	public void testEqualsAndHashCode() {
		
		EqualsVerifier.simple().forClass(ToWoundRoll.class).verify();
		
	}
	
	

}
