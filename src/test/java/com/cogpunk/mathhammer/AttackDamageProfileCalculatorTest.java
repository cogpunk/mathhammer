package com.cogpunk.mathhammer;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class AttackDamageProfileCalculatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalculate() {
	
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(2), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new Dice(3), 1, 13);
		CombatantProfileImpl defenceCombatant = attackCombatant;
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(64,81));
		expMap.put(1, new Fraction(16,243));
		expMap.put(2, new Fraction(16,243).add(new Fraction(1,729)));
		expMap.put(3, new Fraction(16,243).add(new Fraction(2,729)));
		expMap.put(4, new Fraction(3,729));
		expMap.put(5, new Fraction(2,729));
		expMap.put(6, new Fraction(1,729));
		
		assertEquals( expMap , calculator.calculate(attackCombatant, defenceCombatant).map());
		
		
	}

	@Test
	public void testSingleAttackHitProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(1,3));
		expMap.put(1, new Fraction(2,3));
		
		assertEquals( expMap , calculator.calculateHitProbabilityProfile(attackCombatant).map());
		
		
	}
	
	@Test
	public void testMultipleAttackHitProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(2), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(1,9));
		expMap.put(1, new Fraction(4,9));
		expMap.put(2, new Fraction(4,9));
		
		assertEquals( expMap , calculator.calculateHitProbabilityProfile(attackCombatant).map());
		
	}
	
	@Test
	public void testVariableAttackHitProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new Dice(3), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(13,81));
		expMap.put(1, new Fraction(36,81));
		expMap.put(2, new Fraction(24,81));
		expMap.put(3, new Fraction(8,81));
		
		assertEquals( expMap , calculator.calculateHitProbabilityProfile(attackCombatant).map());
		
	}
	
	@Test
	public void testSingleAttackWoundHitProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		CombatantProfileImpl defenceCombatant = attackCombatant;
		
		EventProbabilityProfile<Integer, Fraction> hitProfile = calculator.calculateHitProbabilityProfile(attackCombatant);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(2,3));
		expMap.put(1, new Fraction(1,3));
		
		assertEquals( expMap , calculator.calculateWoundProbabilityProfile(attackCombatant,defenceCombatant, hitProfile).map());
		
		
	}
	
	@Test
	public void testMultipleAttackWoundHitProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(2), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		CombatantProfileImpl defenceCombatant = attackCombatant;
		
		EventProbabilityProfile<Integer, Fraction> hitProfile = calculator.calculateHitProbabilityProfile(attackCombatant);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(4,9));
		expMap.put(1, new Fraction(4,9));
		expMap.put(2, new Fraction(1,9));
		
		assertEquals( expMap , calculator.calculateWoundProbabilityProfile(attackCombatant,defenceCombatant, hitProfile).map());
		
		
	}

	@Test
	public void testSingleAttackUnsavedProbabilityProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		CombatantProfileImpl defenceCombatant = attackCombatant;
		
		EventProbabilityProfile<Integer, Fraction> hitProfile = calculator.calculateHitProbabilityProfile(attackCombatant);
		EventProbabilityProfile<Integer, Fraction> woundProfile = calculator.calculateWoundProbabilityProfile(attackCombatant,defenceCombatant, hitProfile);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(8,9));
		expMap.put(1, new Fraction(1,9));
		
		assertEquals( expMap , calculator.calculateUnsavedProbabilityProfile(attackCombatant, defenceCombatant, woundProfile).map());
		
	}
	
	@Test
	public void testMultipleAttackUnsavedProbabilityProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(2), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		CombatantProfileImpl defenceCombatant = attackCombatant;
		
		EventProbabilityProfile<Integer, Fraction> hitProfile = calculator.calculateHitProbabilityProfile(attackCombatant);
		EventProbabilityProfile<Integer, Fraction> woundProfile = calculator.calculateWoundProbabilityProfile(attackCombatant,defenceCombatant, hitProfile);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(64,81));
		expMap.put(1, new Fraction(16,81));
		expMap.put(2, new Fraction(1,81));
		
		assertEquals( expMap , calculator.calculateUnsavedProbabilityProfile(attackCombatant, defenceCombatant, woundProfile).map());
		
	}

	@Test
	public void testSingleAttackSingleDamageProbabilityProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		CombatantProfileImpl defenceCombatant = attackCombatant;
		
		EventProbabilityProfile<Integer, Fraction> hitProfile = calculator.calculateHitProbabilityProfile(attackCombatant);
		EventProbabilityProfile<Integer, Fraction> woundProfile = calculator.calculateWoundProbabilityProfile(attackCombatant,defenceCombatant, hitProfile);
		EventProbabilityProfile<Integer, Fraction> unsavedProfile = calculator.calculateUnsavedProbabilityProfile(attackCombatant, defenceCombatant, woundProfile);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(8,9));
		expMap.put(1, new Fraction(1,9));
		
		assertEquals( expMap , calculator.calculateDamageProbabilityProfile(attackCombatant, unsavedProfile).map());
		
	}
	
	@Test
	public void testSingleAttackMultipleDamageProbabilityProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(2), 1, 13);
		CombatantProfileImpl defenceCombatant = attackCombatant;
		
		EventProbabilityProfile<Integer, Fraction> hitProfile = calculator.calculateHitProbabilityProfile(attackCombatant);
		EventProbabilityProfile<Integer, Fraction> woundProfile = calculator.calculateWoundProbabilityProfile(attackCombatant,defenceCombatant, hitProfile);
		EventProbabilityProfile<Integer, Fraction> unsavedProfile = calculator.calculateUnsavedProbabilityProfile(attackCombatant, defenceCombatant, woundProfile);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(8,9));
		expMap.put(2, new Fraction(1,9));
		
		assertEquals( expMap , calculator.calculateDamageProbabilityProfile(attackCombatant, unsavedProfile).map());
		
	}
	
	@Test
	public void testSingleAttackVariableDamageProbabilityProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new Dice(3), 1, 13);
		CombatantProfileImpl defenceCombatant = attackCombatant;
		
		EventProbabilityProfile<Integer, Fraction> hitProfile = calculator.calculateHitProbabilityProfile(attackCombatant);
		EventProbabilityProfile<Integer, Fraction> woundProfile = calculator.calculateWoundProbabilityProfile(attackCombatant,defenceCombatant, hitProfile);
		EventProbabilityProfile<Integer, Fraction> unsavedProfile = calculator.calculateUnsavedProbabilityProfile(attackCombatant, defenceCombatant, woundProfile);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(8,9));
		expMap.put(1, new Fraction(1,27));
		expMap.put(2, new Fraction(1,27));
		expMap.put(3, new Fraction(1,27));
		
		assertEquals( expMap , calculator.calculateDamageProbabilityProfile(attackCombatant, unsavedProfile).map());
		
	}
	
	@Test
	public void testMultipleAttackVariableDamageProbabilityProfile() {
		
		AttackDamageProfileCalculator calculator = new AttackDamageProfileCalculator();
		
		CombatantProfileImpl attackCombatant = new CombatantProfileImpl(new FixedValue(2), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new Dice(3), 1, 13);
		CombatantProfileImpl defenceCombatant = attackCombatant;
		
		EventProbabilityProfile<Integer, Fraction> hitProfile = calculator.calculateHitProbabilityProfile(attackCombatant);
		EventProbabilityProfile<Integer, Fraction> woundProfile = calculator.calculateWoundProbabilityProfile(attackCombatant,defenceCombatant, hitProfile);
		EventProbabilityProfile<Integer, Fraction> unsavedProfile = calculator.calculateUnsavedProbabilityProfile(attackCombatant, defenceCombatant, woundProfile);
		
		Map<Integer, Fraction> expMap = new HashMap<Integer, Fraction>();
		expMap.put(0, new Fraction(64,81));
		expMap.put(1, new Fraction(16,243));
		expMap.put(2, new Fraction(16,243).add(new Fraction(1,729)));
		expMap.put(3, new Fraction(16,243).add(new Fraction(2,729)));
		expMap.put(4, new Fraction(3,729));
		expMap.put(5, new Fraction(2,729));
		expMap.put(6, new Fraction(1,729));
		
		assertEquals( expMap , calculator.calculateDamageProbabilityProfile(attackCombatant, unsavedProfile).map());
		
	}

}
