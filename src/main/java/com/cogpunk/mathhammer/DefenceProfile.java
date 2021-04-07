package com.cogpunk.mathhammer;

public interface DefenceProfile {
	
	int getToughness();
	
	int getSave();
	
	int getWounds();
	
	Integer getInvulnerableSave();
	
	ReRoll getSaveReRoll();

}
