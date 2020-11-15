package com.cogpunk.mathhammer;

public class ToHitRoll extends SingleDiceRollDelegate {

	public ToHitRoll(int target, int modifier, ReRoll reroll) {

		roll = new SingleDiceRoll(target, modifier, reroll);
	}

}
