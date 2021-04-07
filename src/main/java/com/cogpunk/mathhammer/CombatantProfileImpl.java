package com.cogpunk.mathhammer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class CombatantProfileImpl implements CombatantProfile {
	
	private final EventProbabilityProfile<Integer, Fraction> attacks;
	
	private final int skill;

	private final int toHitModifier;
	
	private final ReRoll toHitReRoll;
	
	private final int strength;
	
	private final int toughness;
	
	private final int toWoundModifier;
	
	private final ReRoll toWoundReRoll;
	
	private final int save;
	
	private final Integer invulnerableSave;

	private final int armourPenetration;
	
	private final ReRoll saveReRoll;
	
	private final EventProbabilityProfile<Integer, Fraction> damageRoll;
	
	private final int wounds;
	
	private final int cost;
	
	
	public CombatantProfileImpl(EventProbabilityProfile<Integer, Fraction> attacks, int skill, int toHitModifier, ReRoll toHitReRoll, int strength, int toughness,
			int toWoundModifier, ReRoll toWoundReRoll, int save, Integer invulnerableSave, int armourPenetration, ReRoll saveReRoll,
			EventProbabilityProfile<Integer, Fraction> damageRoll, int wounds, int cost) {
		super();
		this.attacks = attacks;
		this.skill = skill;
		this.toHitModifier = toHitModifier;
		this.toHitReRoll = toHitReRoll;
		this.strength = strength;
		this.toughness = toughness;
		this.toWoundModifier = toWoundModifier;
		this.toWoundReRoll = toWoundReRoll;
		this.save = save;
		this.invulnerableSave = invulnerableSave;
		this.armourPenetration = armourPenetration;
		this.saveReRoll = saveReRoll;
		this.damageRoll = damageRoll;
		this.wounds = wounds;
		this.cost = cost;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public int getSave() {
		return save;
	}

	@Override
	public ReRoll getSaveReRoll() {
		return saveReRoll;
	}

	@Override
	public int getSkill() {
		return skill;
	}

	@Override
	public int getStrength() {
		return strength;
	}

	@Override
	public int getArmourPenetration() {
		return armourPenetration;
	}

	@Override
	public ReRoll getToHitReRoll() {
		return toHitReRoll;
	}

	@Override
	public ReRoll getToWoundReRoll() {
		return toWoundReRoll;
	}

	@Override
	public int getToHitModifier() {
		return toHitModifier;
	}

	@Override
	public int getToWoundModifier() {
		return toWoundModifier;
	}

	@Override
	public EventProbabilityProfile<Integer, Fraction> getDamageRoll() {
		return damageRoll;
	}

	@Override
	public Integer getInvulnerableSave() {
		return invulnerableSave;
	}
	
	@Override
	public int getWounds() {
		return wounds;
	}
	
	@Override
	public EventProbabilityProfile<Integer, Fraction> getAttacks() {
		return attacks;
	}
	
	@Override
	public int getCost() {
		return cost;
	}
	
	

	@Override
	public String toString() {
		return "CombatantProfile [skill=" + skill + ", toHitModifier=" + toHitModifier + ", toHitReRoll=" + toHitReRoll
				+ ", strength=" + strength + ", toughness=" + toughness + ", toWoundModifier=" + toWoundModifier
				+ ", toWoundReRoll=" + toWoundReRoll + ", save=" + save + ", invulnerableSave=" + invulnerableSave
				+ ", armourPenetration=" + armourPenetration + ", saveReRoll=" + saveReRoll + ", damageRoll="
				+ damageRoll + ", wounds=" + wounds + ", cost=" + cost + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof CombatantProfileImpl)) {
			return false;
		}
		CombatantProfileImpl castOther = (CombatantProfileImpl) other;
		return new EqualsBuilder().append(attacks, castOther.attacks).append(skill, castOther.skill)
				.append(toHitModifier, castOther.toHitModifier).append(toHitReRoll, castOther.toHitReRoll)
				.append(strength, castOther.strength).append(toughness, castOther.toughness)
				.append(toWoundModifier, castOther.toWoundModifier).append(toWoundReRoll, castOther.toWoundReRoll)
				.append(save, castOther.save).append(invulnerableSave, castOther.invulnerableSave)
				.append(armourPenetration, castOther.armourPenetration).append(saveReRoll, castOther.saveReRoll)
				.append(damageRoll, castOther.damageRoll).append(wounds, castOther.wounds).append(cost, castOther.cost)
				.isEquals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(attacks).append(skill).append(toHitModifier).append(toHitReRoll)
				.append(strength).append(toughness).append(toWoundModifier).append(toWoundReRoll).append(save)
				.append(invulnerableSave).append(armourPenetration).append(saveReRoll).append(damageRoll).append(wounds)
				.append(cost).toHashCode();
	}

	
	

}
