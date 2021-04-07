package com.cogpunk.mathhammer;

import static org.junit.Assert.assertEquals;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

import com.cogpunk.math.FractionOperator;

public class StatisticsTest {
	
	private Statistics statistics;
	
	@Before
	public void setUp() {
		statistics = new Statistics(new AverageRollCalculator(new FractionOperator()), new AttackDamageProfileCalculator());
	}
	

	@Test
	public void testKillRatio() {
		
		// Combatant One has a damage probability of 2/3 . 2/3 .1/2 = 4/18 or 2/9
		// Combatant Two has a damage probability of 1/2 . 2/3 . 1/3 = 2/18 = 1/9
		// Both have 1 wound and 1 attack, so it will take 9/2 turns for One to kill Two, and 18/2 turns for Two to kill One
		// So the ratio is (18/2) / (9/2) = 18 / 9, or 2
	
		CombatantProfileImpl combatantOne = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		CombatantProfileImpl combatantTwo = new CombatantProfileImpl(new FixedValue(1), 4, 0, ReRoll.NONE, 5, 3, 0, ReRoll.NONE, 4, null, 0, ReRoll.NONE, new FixedValue(1), 1, 7);
		
		assertEquals(new Fraction(2), statistics.killRatio(combatantOne, combatantTwo));
		assertEquals(new Fraction(1,2), statistics.killRatio(combatantTwo, combatantOne));
		
		// Doubling the wounds of combatant two will even the ratio
		
		combatantTwo = new CombatantProfileImpl(new FixedValue(1), 4, 0, ReRoll.NONE, 5, 3, 0, ReRoll.NONE, 4, null, 0, ReRoll.NONE, new FixedValue(1), 2, 7);
		
		assertEquals(new Fraction(1), statistics.killRatio(combatantOne, combatantTwo));
		assertEquals(new Fraction(1), statistics.killRatio(combatantTwo, combatantOne));
		
	}

	@Test
	public void testKillPointsRatio() {
		
		// Kill Ratios as in #testKillRatio, but take into account the more expensive cost of combatant One
		// 2 / (13/7) = 14/13
		// So 13 points of Combatant One = 14 Points of Combatant Two
		
		CombatantProfileImpl combatantOne = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		CombatantProfileImpl combatantTwo = new CombatantProfileImpl(new FixedValue(1), 4, 0, ReRoll.NONE, 5, 3, 0, ReRoll.NONE, 4, null, 0, ReRoll.NONE, new FixedValue(1), 1, 7);
		
		assertEquals(new Fraction(14,13), statistics.killPointsRatio(combatantOne, combatantTwo));
		
		
	}
	
	@Test
	public void testKillPointsCost() {
		
		// Combatants as per #testKillRatio
		// Will take 9/2 turns for One to kill Two. Killing Two is 7 points of Damage and costs 13 points
		// 9/2 . 13/7 = 117/14
		
		CombatantProfileImpl combatantOne = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		CombatantProfileImpl combatantTwo = new CombatantProfileImpl(new FixedValue(1), 4, 0, ReRoll.NONE, 5, 3, 0, ReRoll.NONE, 4, null, 0, ReRoll.NONE, new FixedValue(1), 1, 7);
		
		assertEquals(new Fraction(117,14), statistics.killPointsCost(combatantOne, combatantTwo));
		
		// Will take 9 turns for Two to kill One. Killing Two is 13 points of Damage and costs 7 points
		// 9 . 7/13 = 63/13
		
		assertEquals(new Fraction(63, 13), statistics.killPointsCost(combatantTwo, combatantOne));
		
		
	}
	
	@Test
	public void testTurnsRequiredToKill() {
		
		// Combatant One has a damage probability of 2/3 . 2/3 .1/2 = 4/18 or 2/9
		// Combatant Two has a damage probability of 1/2 . 2/3 . 1/3 = 2/18 = 1/9
		// Both have 1 wound and 1 attack, so it will take 9/2 turns for One to kill Two, and 18/2 turns for Two to kill One
		
		CombatantProfileImpl combatantOne = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		CombatantProfileImpl combatantTwo = new CombatantProfileImpl(new FixedValue(1), 4, 0, ReRoll.NONE, 5, 3, 0, ReRoll.NONE, 4, null, 0, ReRoll.NONE, new FixedValue(1), 1, 7);
		
		assertEquals(new Fraction(9,2), statistics.turnsRequiredToKill(combatantOne, combatantTwo));
		assertEquals(new Fraction(9), statistics.turnsRequiredToKill(combatantTwo, combatantOne));
		
		// increase strength of attacker one to 6 
		// Combatant One has a damage probability of 2/3 . 5/6 .1/2 = 10/36 or 5/18
		
		combatantOne = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 6, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		
		assertEquals(new Fraction(18,5), statistics.turnsRequiredToKill(combatantOne, combatantTwo));
		
		// improve save of Two to 2+
		// Combatant One has a damage probability of 2/3 . 5/6 . 1/6 = 10/108 or 5/54
		
		combatantTwo = new CombatantProfileImpl(new FixedValue(1), 4, 0, ReRoll.NONE, 5, 3, 0, ReRoll.NONE, 2, null, 0, ReRoll.NONE, new FixedValue(1), 1, 7);
		
		assertEquals(new Fraction(54,5), statistics.turnsRequiredToKill(combatantOne, combatantTwo));
		
		
	}

}
