package com.cogpunk.mathhammer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.FractionOperator;
import com.cogpunk.math.probability.ComparableEventProbabilityProfile;
import com.cogpunk.math.probability.ComparableEventProbabilityProfileImpl;
import com.cogpunk.math.probability.ConditionalReevaluationProbabilityProfile;
import com.cogpunk.math.probability.EventLessThanSelector;
import com.cogpunk.math.probability.EventProbabilityProfile;
import com.cogpunk.math.probability.SimpleProbabilityProfileImpl;

public class SingleDiceRoll implements EventProbabilityProfile<Integer, Fraction> {

	private final int target;

	private final int modifier;

	private final ReRoll reroll;

	private EventProbabilityProfile<Integer, Fraction> profile;

	private EventProbabilityProfile<Integer, Fraction> diceProfile;

	public SingleDiceRoll(int target, int modifier, ReRoll reroll) {
		super();

		this.target = target;
		this.modifier = modifier;
		this.reroll = reroll;

		calculate();
	}

	protected void calculate() {

		EventProbabilityProfile<Integer, Fraction> localProfile = new Dice(6);

		FractionOperator fractionOperator = new FractionOperator();

		int localTarget = Math.max(2, target);

		int modifiedTarget = Math.max(2, target - modifier);

		switch (reroll) {
		case ONE: {
			localProfile = new ConditionalReevaluationProbabilityProfile<>(localProfile,
					new EventLessThanSelector<Integer, Fraction>(2), 1, fractionOperator);
			break;
		}
		case FAIL: {
			//
			// RErolling of failures is optional, and there is no point re-rolling a fail
			// that will be a success upon modification,
			// hence taking the lower of he two targets to trigger a re-roll
			//
			localProfile = new ConditionalReevaluationProbabilityProfile<>(localProfile,
					new EventLessThanSelector<Integer, Fraction>(Math.min(localTarget, modifiedTarget)), 1,
					fractionOperator);
			break;
		}
		default: {
			// do nothing
			break;
		}
		}

		ComparableEventProbabilityProfile<Integer, Fraction> compLocalProfile = new ComparableEventProbabilityProfileImpl<>(
				localProfile.map(), fractionOperator);

		Fraction passProb = compLocalProfile.getProbabilityGreaterThanOrEqualTo(modifiedTarget);

		Map<Integer, Fraction> resultMap = new HashMap<>();
		resultMap.put(1, passProb);
		resultMap.put(0, fractionOperator.subtract(Fraction.ONE, passProb));

		profile = new SimpleProbabilityProfileImpl<>(resultMap);
		diceProfile = compLocalProfile;

	}

	@Override
	public Fraction getProbability(Integer event) {
		return profile.getProbability(event);
	}

	@Override
	public Map<Integer, Fraction> map() {
		return profile.map();
	}

	public EventProbabilityProfile<Integer, Fraction> getDiceProfile() {
		return diceProfile;
	}

	public int getTarget() {
		return target;
	}

	public int getModifier() {
		return modifier;
	}

	public ReRoll getReroll() {
		return reroll;
	}

	public EventProbabilityProfile<Integer, Fraction> getProfile() {
		return profile;
	}

	@Override
	public String toString() {
		return "SingleDiceRoll [target=" + target + ", modifier=" + modifier + ", reroll=" + reroll + ", profile="
				+ profile + ", diceProfile=" + diceProfile + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SingleDiceRoll)) {
			return false;
		}
		SingleDiceRoll castOther = (SingleDiceRoll) other;
		return new EqualsBuilder().append(target, castOther.target).append(modifier, castOther.modifier)
				.append(reroll, castOther.reroll).append(profile, castOther.profile)
				.append(diceProfile, castOther.diceProfile).isEquals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(target).append(modifier).append(reroll).append(profile).append(diceProfile)
				.toHashCode();
	}

	

}
