package com.cogpunk.mathhammer;

import org.apache.commons.math3.fraction.Fraction;

import com.cogpunk.math.NumberOperator;
import com.cogpunk.math.probability.AverageEventCalculator;
import com.cogpunk.math.probability.EventProbabilityProfile;

public class AverageRollCalculator extends AverageEventCalculator<Integer, Fraction> {
	
	private NumberOperator<Fraction> probabilityNumberOperator;
	
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

}
