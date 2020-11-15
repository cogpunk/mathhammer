package com.cogpunk.mathhammer;

import static org.junit.Assert.*;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

import com.cogpunk.math.probability.EventGreaterThanSelector;
import com.cogpunk.math.probability.EventProbabilityProfile;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CombatantProfileImplTest {
	
	private CombatantProfileImpl profile;
	

	@Before
	public void setUp() throws Exception {
		
		profile = new CombatantProfileImpl(new FixedValue(1), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new FixedValue(1), 1, 13);
		
	}

	@Test
	public void testEqualsAndHashCode() {
		
		EqualsVerifier.simple().forClass(CombatantProfileImpl.class).verify();
		
	}
	
	@Test
	public void testGetters() {
		
		assertEquals(4,profile.getToughness());
		assertEquals(3,profile.getSave());
		assertEquals(ReRoll.NONE,profile.getSaveReRoll());
		assertEquals(3,profile.getSkill());
		assertEquals(4,profile.getStrength());
		assertEquals(0,profile.getArmourPenetration());
		assertEquals(ReRoll.NONE,profile.getToHitReRoll());
		assertEquals(ReRoll.NONE,profile.getToWoundReRoll());
		assertEquals(0,profile.getToHitModifier());
		assertEquals(0,profile.getToWoundModifier());
		assertEquals(null,profile.getInvulnerableSave());
		assertEquals(1,profile.getWounds());
		assertEquals(13,profile.getCost());
		

	}

}
