package com.cogpunk.mathhammer;

import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class AttackDamageProfile implements EventProbabilityProfile<Integer, Fraction> {

	private final AttackProfile attackProfile;

	private final DefenceProfile defenceProfile;

	private final EventProbabilityProfile<Integer, Fraction> probabilityProfile;

	public AttackDamageProfile(AttackProfile attackProfile, DefenceProfile defenceProfile,
			EventProbabilityProfile<Integer, Fraction> probabilityProfile) {
		super();
		this.attackProfile = attackProfile;
		this.defenceProfile = defenceProfile;
		this.probabilityProfile = probabilityProfile;

	}

	public Fraction getProbability(Integer event) {
		return probabilityProfile.getProbability(event);
	}

	public Map<Integer, Fraction> map() {
		return probabilityProfile.map();
	}

	@Override
	public String toString() {
		return "AttackDamageProfile [attackProfile=" + attackProfile + ", defenseProfile=" + defenceProfile
				+ ", probabilityProfile=" + probabilityProfile + "]";
	}

	public AttackProfile getAttackProfile() {
		return attackProfile;
	}

	public DefenceProfile getDefenseProfile() {
		return defenceProfile;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AttackDamageProfile)) {
			return false;
		}
		AttackDamageProfile castOther = (AttackDamageProfile) other;
		return new EqualsBuilder().append(attackProfile, castOther.attackProfile)
				.append(defenceProfile, castOther.defenceProfile)
				.append(probabilityProfile, castOther.probabilityProfile).isEquals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(attackProfile).append(defenceProfile).append(probabilityProfile)
				.toHashCode();
	}

}
