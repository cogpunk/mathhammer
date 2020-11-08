package com.cogpunk.mathhammer;

import org.apache.commons.math3.fraction.Fraction;

public class PointEffectiveness {
	
	private AverageRollCalculator averageRollCalculator;
	
	public PointEffectiveness(AverageRollCalculator averageRollCalculator) {
		super();
		this.averageRollCalculator = averageRollCalculator;
	}


	/**
	 * 
	 * @param attacker The profile of the attacker
	 * @param defender The profile of the defender
	 * @return The points effectiveness based on the mean average damage done.
	 */
	public Fraction calculateAverage(CombatantProfile attacker, CombatantProfile defender) {
		
		AttackDamageProfile attackDamageProfile = new AttackDamageProfileCalculator().calculate(attacker, defender);
		
		Fraction attacksToKillDefender = new Fraction(defender.getWounds()).divide(averageRollCalculator.mean(attackDamageProfile));
		
		Fraction pointsCostToKillDefender = new Fraction(attacker.getCost()).multiply(attacksToKillDefender);
		
		return new Fraction(defender.getCost()).divide(pointsCostToKillDefender);
		
	}

}
