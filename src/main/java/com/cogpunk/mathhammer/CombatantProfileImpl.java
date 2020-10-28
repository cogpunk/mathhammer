package com.cogpunk.mathhammer;

import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.probability.EventProbabilityProfile;

public class CombatantProfileImpl implements CombatantProfile {
	
	private EventProbabilityProfile<Integer, Fraction> attacks;
	
	private int skill;

	private int toHitModifier;
	
	private ReRoll toHitReRoll;
	
	private int strength;
	
	private int toughness;
	
	private int toWoundModifier;
	
	private ReRoll toWoundReRoll;
	
	private int save;
	
	private Integer invulnerableSave;

	private int armourPenetration;
	
	private ReRoll saveReRoll;
	
	public EventProbabilityProfile<Integer, Fraction> damageRoll;
	
	public int wounds;
	
	public int cost;
	
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + armourPenetration;
		result = prime * result + ((attacks == null) ? 0 : attacks.hashCode());
		result = prime * result + cost;
		result = prime * result + ((damageRoll == null) ? 0 : damageRoll.hashCode());
		result = prime * result + ((invulnerableSave == null) ? 0 : invulnerableSave.hashCode());
		result = prime * result + save;
		result = prime * result + ((saveReRoll == null) ? 0 : saveReRoll.hashCode());
		result = prime * result + skill;
		result = prime * result + strength;
		result = prime * result + toHitModifier;
		result = prime * result + ((toHitReRoll == null) ? 0 : toHitReRoll.hashCode());
		result = prime * result + toWoundModifier;
		result = prime * result + ((toWoundReRoll == null) ? 0 : toWoundReRoll.hashCode());
		result = prime * result + toughness;
		result = prime * result + wounds;
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
		CombatantProfileImpl other = (CombatantProfileImpl) obj;
		if (armourPenetration != other.armourPenetration)
			return false;
		if (attacks == null) {
			if (other.attacks != null)
				return false;
		} else if (!attacks.equals(other.attacks))
			return false;
		if (cost != other.cost)
			return false;
		if (damageRoll == null) {
			if (other.damageRoll != null)
				return false;
		} else if (!damageRoll.equals(other.damageRoll))
			return false;
		if (invulnerableSave == null) {
			if (other.invulnerableSave != null)
				return false;
		} else if (!invulnerableSave.equals(other.invulnerableSave))
			return false;
		if (save != other.save)
			return false;
		if (saveReRoll != other.saveReRoll)
			return false;
		if (skill != other.skill)
			return false;
		if (strength != other.strength)
			return false;
		if (toHitModifier != other.toHitModifier)
			return false;
		if (toHitReRoll != other.toHitReRoll)
			return false;
		if (toWoundModifier != other.toWoundModifier)
			return false;
		if (toWoundReRoll != other.toWoundReRoll)
			return false;
		if (toughness != other.toughness)
			return false;
		if (wounds != other.wounds)
			return false;
		return true;
	}
	
	
	
	
	
	

}
