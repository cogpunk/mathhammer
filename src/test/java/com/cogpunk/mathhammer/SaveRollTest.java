package com.cogpunk.mathhammer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class SaveRollTest extends SingleDiceRollTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Override
	public EventProbabilityProfile<Integer, Fraction> getProfile(int target, int modifier, ReRoll reroll) {
		return new SaveRoll(target, null, modifier, reroll);
	}
	
	@Test
	public void testInvulnerable() {
		
		EventProbabilityProfile<Integer, Fraction> roll = new SaveRoll(3, 5, 0, ReRoll.NONE);
		
		assertEquals(new Fraction(2,3), roll.getProbability(1));
		assertEquals(new Fraction(1,3), roll.getProbability(0));
		
	}
	
	@Test
	public void testInvulnerableWithReRoll() {
		
		EventProbabilityProfile<Integer, Fraction> roll = new SaveRoll(3, 4, -2, ReRoll.FAIL);
		
		assertEquals(new Fraction(3,4), roll.getProbability(1));
		assertEquals(new Fraction(1,4), roll.getProbability(0));
		
	}
	
	@Test
	public void testInvulnerableWithModifier() {
		
		EventProbabilityProfile<Integer, Fraction> roll = new SaveRoll(3, 5, -3, ReRoll.NONE);
		
		assertEquals(new Fraction(2,6), roll.getProbability(1));
		assertEquals(new Fraction(4,6), roll.getProbability(0));
		
	}
	
	
	@Test
	public void testHashCode() {
		
		EventProbabilityProfile<Integer, Fraction> profile = new SaveRoll(3, 4, -2, ReRoll.FAIL);
		EventProbabilityProfile<Integer, Fraction> same = new SaveRoll(3, 4, -2, ReRoll.FAIL);
		
		assertEquals(profile.hashCode(), same.hashCode());
	}

	@Test
	public void testEqualsObject() {
		
		EventProbabilityProfile<Integer, Fraction> profile = new SaveRoll(3, 4, -2, ReRoll.FAIL);
		EventProbabilityProfile<Integer, Fraction> same = new SaveRoll(3, 4, -2, ReRoll.FAIL);
		EventProbabilityProfile<Integer, Fraction> different = new SaveRoll(3, 5, -2, ReRoll.FAIL);
		
		
		assertEquals(profile, same);
		assertEquals(same, profile);
		assertNotEquals(profile, different);
		assertNotEquals(different, profile);
	}

}
