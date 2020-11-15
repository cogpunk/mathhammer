package com.cogpunk.mathhammer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

public class DiceTest {
	
	Dice dice;
	
	@Before
	public void setUp() throws Exception {
		dice = new Dice(6);
	}

	@Test
	public void testHashCode() {
		assertEquals(new Dice(6).hashCode(), dice.hashCode());
	}

	@Test
	public void testGetProbability() {
		assertEquals(new Fraction(1,6), dice.getProbability(1));
	}

	@Test
	public void testGetProbabilityGreaterThanOrEqualTo() {
		assertEquals(new Fraction(1,3), dice.getProbabilityGreaterThanOrEqualTo(5));
	}

	@Test
	public void testGetProbabilityLessThanOrEqualTo() {
		assertEquals(new Fraction(5,6), dice.getProbabilityLessThanOrEqualTo(5));
	}

	@Test
	public void testGetProbabilityGreaterThan() {
		assertEquals(new Fraction(1,6), dice.getProbabilityGreaterThan(5));
	}

	@Test
	public void testGetProbabilityLessThan() {
		assertEquals(new Fraction(2,3), dice.getProbabilityLessThan(5));
	}

	@Test
	public void testEqualsObject() {
		assertEquals(new Dice(6), dice);
		assertEquals(dice, new Dice(6));
	}

}
