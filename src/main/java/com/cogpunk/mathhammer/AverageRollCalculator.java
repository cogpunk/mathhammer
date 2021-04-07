package com.cogpunk.mathhammer;

import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.NumberOperator;
import com.cogpunk.math.probability.AverageEventCalculator;
import com.cogpunk.math.probability.EventProbabilityProfile;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class AverageRollCalculator extends AverageEventCalculator<Integer, Fraction> {
	
	private final NumberOperator<Fraction> probabilityNumberOperator;
	
	public AverageRollCalculator(NumberOperator<Fraction> probabilityNumberOperator) {
		super(probabilityNumberOperator);
		this.probabilityNumberOperator = probabilityNumberOperator;
	}

	/**
	 * Because of the division, the default has to be a double
	 * @param profile The Probability profile
	 * @return The mean average
	 */
	public Fraction mean(EventProbabilityProfile<Integer,Fraction> profile) {
		
		// Sum all events, weighted by their probabilities, then divide by the total probability
		
		Fraction totalProb = calculateTotalProbability(profile);
	
		Fraction totalEvent = probabilityNumberOperator.cast(0);
		
		for (Integer e : profile.map().keySet()) {
			
			totalEvent = probabilityNumberOperator.add(
					probabilityNumberOperator.multiply(
							probabilityNumberOperator.cast(e), 
							profile.getProbability(e)
							), 
					totalEvent
					);
			
		}
		
		
		return probabilityNumberOperator.divide(totalEvent, totalProb);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AverageRollCalculator)) {
			return false;
		}
		AverageRollCalculator castOther = (AverageRollCalculator) other;
		return new EqualsBuilder().appendSuper(super.equals(castOther)).append(probabilityNumberOperator, castOther.probabilityNumberOperator).isEquals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().appendSuper(super.hashCode()).append(probabilityNumberOperator).toHashCode();
	}
	
}
