package com.cogpunk.mathhammer;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

import com.cogpunk.math.FractionOperator;
import com.cogpunk.math.probability.SimpleProbabilityProfileImpl;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AverageRollCalculatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMean() {
		
		Map<Integer, Fraction> map = new HashMap<Integer, Fraction>();
		map.put(0, new Fraction(1,3));
		map.put(1, new Fraction(1,3));
		map.put(2, new Fraction(2,3));
		map.put(3, new Fraction(2,3));
		
		AverageRollCalculator calc = new AverageRollCalculator(new FractionOperator());
		
		assertEquals(new Fraction(11, 6), calc.mean(new SimpleProbabilityProfileImpl<Integer, Fraction>(map)));
		
		map.put(3, new Fraction(3,3));
		
		assertEquals(new Fraction(14, 7), calc.mean(new SimpleProbabilityProfileImpl<Integer, Fraction>(map)));
		
	}
	
	@Test
	public void testEqualsAndHashCode() {
		
		EqualsVerifier.simple().forClass(AverageRollCalculator.class).verify();
		
	}

}
