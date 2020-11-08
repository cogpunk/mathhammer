package com.cogpunk.mathhammer;

import org.apache.commons.math3.fraction.Fraction;

public class Statistics {
	
	private AverageRollCalculator averageRollCalculator;
	
	private AttackDamageProfileCalculator attackDamageProfileCalculator;
	
	public Statistics(AverageRollCalculator averageRollCalculator,
			AttackDamageProfileCalculator attackDamageProfileCalculator) {
		super();
		this.averageRollCalculator = averageRollCalculator;
		this.attackDamageProfileCalculator = attackDamageProfileCalculator;
	}


	/**
	 * Calculates the kill ratio between the combatants.  If combatant 1 will kill 2 of combtanat2 for every loss, then the returned value is 2
	 * @param combatant1 1st combatant
	 * @param combatant2 2nd combatant
	 * @return the kill ratio between the combatants
	 */
	public Fraction killRatio(CombatantProfile combatant1, CombatantProfile combatant2) {
		
		Fraction firstToKillSecond = turnsRequiredToKill(combatant1, combatant2);
		
		Fraction secondToKillFirst = turnsRequiredToKill(combatant2, combatant1);
		
		return secondToKillFirst.divide(firstToKillSecond);
		
	}
	
	/**
	 * @param attacker The profile of the attacker
	 * @param defender The profile of the defender
	 * @return The cost in points for attacker to inflict the equivalent on 1 point of damage on the defender in a given turn
	 */
	public Fraction killPointsCost(CombatantProfile attacker, CombatantProfile defender) {
		
		Fraction attackerToKillDefnder = turnsRequiredToKill(attacker, defender);
		
		return new Fraction(attacker.getCost(), defender.getCost()).multiply(attackerToKillDefnder);
		
	}
	
	/**
	 * The Kill Ratio normalised by the points value. For equal points costs then this will be the same as killRatio, however if the cost of combatant1 is double that of combatant2
	 * then the returned value will halved.
	 * 
	 * This is a basic measure of the cost effectiveness of One when facing Two; 1 equates to an even deal, &lt; 1 means it is cheap and &gt; 1 means it is over priced
	 * 
	 * @param combatant1 1st combatant
	 * @param combatant2 2nd combatant
	 * @return the kill point ratio between the combatants
	 */
	public Fraction killPointsRatio(CombatantProfile combatant1, CombatantProfile combatant2) {
		
		Fraction killRatio = killRatio(combatant1, combatant2);
		
		return killRatio.divide(new Fraction(combatant1.getCost(), combatant2.getCost()));
		
		
	}
	
	/**
	 * @param attacker The profile of the attacker
	 * @param defender The profile of the defender
	 * @return The number of attacker required for the attacker to kill the defender
	 */
	public Fraction turnsRequiredToKill(AttackProfile attacker, DefenceProfile defender) {
		
		AttackDamageProfile attackDamageProfile = attackDamageProfileCalculator.calculate(attacker, defender);
		
		return new Fraction(defender.getWounds()).divide(averageRollCalculator.mean(attackDamageProfile));

		
	}

}
