package com.cogpunk.mathhammer;

import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
		
		return new HashCodeBuilder(13, 91).
			       append(attackProfile).
			       append(defenceProfile).
			       append(probabilityProfile).
			       toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttackDamageProfile rhs = (AttackDamageProfile) obj;
		
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(attackProfile, rhs.attackProfile)
				.append(defenceProfile, rhs.defenceProfile)
				.append(probabilityProfile, rhs.probabilityProfile)
				.isEquals();
		
	}
	
	

	

}
