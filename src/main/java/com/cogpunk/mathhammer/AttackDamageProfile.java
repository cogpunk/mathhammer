package com.cogpunk.mathhammer;

import java.util.Map;

import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class AttackDamageProfile implements EventProbabilityProfile<Integer, Fraction> {
	
	private AttackProfile attackProfile;
	
	private DefenceProfile defenceProfile;
	
	private EventProbabilityProfile<Integer, Fraction> probabilityProfile;
	
	public AttackDamageProfile(AttackProfile attackProfile, DefenceProfile defenceProfile, EventProbabilityProfile<Integer, Fraction> probabilityProfile) {
		super();
		this.attackProfile = attackProfile;
		this.defenceProfile = defenceProfile;
		this.probabilityProfile = probabilityProfile;
		
	}

	@Override
	public Fraction getProbability(Integer event) {
		return probabilityProfile.getProbability(event);
	}

	@Override
	public Map<Integer, Fraction> map() {
		return probabilityProfile.map();
	}
	

	@Override
	public String toString() {
		return "AttackDamageProfile [attackProfile=" + attackProfile + ", defenseProfile=" + defenceProfile + ", probabilityProfile="
				+ probabilityProfile + "]";
	}
	
	public AttackProfile getAttackProfile() {
		return attackProfile;
	}

	public DefenceProfile getDefenseProfile() {
		return defenceProfile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attackProfile == null) ? 0 : attackProfile.hashCode());
		result = prime * result + ((defenceProfile == null) ? 0 : defenceProfile.hashCode());
		result = prime * result + ((probabilityProfile == null) ? 0 : probabilityProfile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttackDamageProfile other = (AttackDamageProfile) obj;
		if (attackProfile == null) {
			if (other.attackProfile != null)
				return false;
		} else if (!attackProfile.equals(other.attackProfile))
			return false;
		if (defenceProfile == null) {
			if (other.defenceProfile != null)
				return false;
		} else if (!defenceProfile.equals(other.defenceProfile))
			return false;
		if (probabilityProfile == null) {
			if (other.probabilityProfile != null)
				return false;
		} else if (!probabilityProfile.equals(other.probabilityProfile))
			return false;
		return true;
	}
	
	

	

}
