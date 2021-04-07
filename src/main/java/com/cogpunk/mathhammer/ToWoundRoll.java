package com.cogpunk.mathhammer;

public class ToWoundRoll extends SingleDiceRollDelegate  {
	
	public ToWoundRoll(int strength, int toughness, int modifier, ReRoll reroll) {
		
		roll = new SingleDiceRoll(getTarget(strength, toughness), modifier, reroll);
		
	}
	
	public ToWoundRoll(int target, int modifier, ReRoll reroll) {
		
		roll = new SingleDiceRoll(target, modifier, reroll);
	}
	
	private int getTarget(int strength, int toughness) {
		
		int target;
		
		if (strength >= 2*toughness) {
			target = 2;
		} else if (strength > toughness) {
			target = 3;
		} else if (strength == toughness) {
			target = 4;
		} else if (2*strength <= toughness) {
			target = 6;
		} else {
			// strength < toughness
			target = 5;
		} 
		
		return target;
		
	}
	

}
