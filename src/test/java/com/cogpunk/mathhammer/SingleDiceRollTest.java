package com.cogpunk.mathhammer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class SingleDiceRollTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testStraightRoll() {
		
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(3, 0, ReRoll.NONE);
		
		
		assertEquals(new Fraction(2,3), roll.getProbability(1));
		assertEquals(new Fraction(1,3), roll.getProbability(0));
		
	}
	
	@Test
	public void testLowTarget() {
		
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(0, 0, ReRoll.NONE);
		
		assertEquals(new Fraction(5,6), roll.getProbability(1));
		assertEquals(new Fraction(1,6), roll.getProbability(0));
		
	}
	
	@Test
	public void testHighTarget() {
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(7, 0, ReRoll.NONE);
		
		
		assertEquals(new Fraction(0,6), roll.getProbability(1));
		assertEquals(new Fraction(6,6), roll.getProbability(0));
	}
	
	@Test
	public void testReRollOnes() {
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(4, 0, ReRoll.ONE);
		
		
		assertEquals(new Fraction(21,36), roll.getProbability(1));
		assertEquals(new Fraction(15,36), roll.getProbability(0));
	}
	
	@Test
	public void testReRollMisses() {
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(4, 0, ReRoll.FAIL);
		
		
		assertEquals(new Fraction(3,4), roll.getProbability(1));
		assertEquals(new Fraction(1,4), roll.getProbability(0));
	}
	
	@Test
	public void testPositiveModifier() {
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(4, 1, ReRoll.NONE);
		
		assertEquals(new Fraction(2,3), roll.getProbability(1));
		assertEquals(new Fraction(1,3), roll.getProbability(0));
	}
	
	@Test
	public void testPositiveModifierBelowTwo() {
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(4, 4, ReRoll.NONE);
		
		assertEquals(new Fraction(5,6), roll.getProbability(1));
		assertEquals(new Fraction(1,6), roll.getProbability(0));
	}
	
	@Test
	public void testNegativeModifier() {
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(4, -1, ReRoll.NONE);
		
		assertEquals(new Fraction(1,3), roll.getProbability(1));
		assertEquals(new Fraction(2,3), roll.getProbability(0));
	}
	
	@Test
	public void testNegativeModifierAboveSix() {
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(4, -3, ReRoll.NONE);
		
		assertEquals(new Fraction(0,6), roll.getProbability(1));
		assertEquals(new Fraction(6,6), roll.getProbability(0));
	}
	
	@Test
	public void testPositiveModifiedRerollOnFail() {
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(5, 1, ReRoll.FAIL);
		
		assertEquals(new Fraction(27,36), roll.getProbability(1));
		assertEquals(new Fraction(9,36), roll.getProbability(0));
	}
	
	@Test
	public void testNegativeModifiedRerollOnFail() {
		EventProbabilityProfile<Integer, Fraction> roll = getProfile(5, -1, ReRoll.FAIL);
		
		assertEquals(new Fraction(10,36), roll.getProbability(1));
		assertEquals(new Fraction(26,36), roll.getProbability(0));
	}
	
	public EventProbabilityProfile<Integer, Fraction> getProfile(int target, int modifier, ReRoll reroll) {
		return new SingleDiceRoll(target, modifier, reroll);
	}
	
	@Test
	public void testHashCode() {
		
		EventProbabilityProfile<Integer, Fraction> profile = getProfile(3, 0, ReRoll.NONE);
		EventProbabilityProfile<Integer, Fraction> same = getProfile(3, 0, ReRoll.NONE);
		
		assertEquals(profile.hashCode(), same.hashCode());
	}

	@Test
	public void testEqualsObject() {
		
		EventProbabilityProfile<Integer, Fraction> profile = getProfile(3, 0, ReRoll.NONE);
		EventProbabilityProfile<Integer, Fraction> same = getProfile(3, 0, ReRoll.NONE);
		EventProbabilityProfile<Integer, Fraction> different = getProfile(4, 0, ReRoll.NONE);
		
		
		assertEquals(profile, same);
		assertEquals(same, profile);
		assertNotEquals(profile, different);
		assertNotEquals(different, profile);
	}
	
	@Test
	public void testGetters() {
		SingleDiceRoll roll = new SingleDiceRoll(2, 4, ReRoll.NONE);
		
		assertEquals(2, roll.getTarget());
		assertEquals(4, roll.getModifier());
		assertEquals(ReRoll.NONE, roll.getReroll());
		
	}
	

}
