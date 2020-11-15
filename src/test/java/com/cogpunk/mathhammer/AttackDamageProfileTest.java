package com.cogpunk.mathhammer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AttackDamageProfileTest {
	
	public CombatantProfileImpl attackProfile = new CombatantProfileImpl(new FixedValue(2), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new Dice(3), 1, 13);
	public CombatantProfileImpl defenceProfile = new CombatantProfileImpl(new FixedValue(3), 3, 0, ReRoll.NONE, 4, 4, 0, ReRoll.NONE, 3, null, 0, ReRoll.NONE, new Dice(3), 1, 13);
	public AttackDamageProfile attackDamageProfile = new AttackDamageProfile(attackProfile, defenceProfile, new Dice(6));

	@Test
	public void testGetters() {
		
		
		assertEquals(attackDamageProfile.getAttackProfile(), attackProfile);
		assertEquals(attackDamageProfile.getDefenseProfile(), defenceProfile);
		assertEquals(attackDamageProfile.map(), new Dice(6).map());
		
	}

	@Test
	public void testEqualsAndHashCode() {
		
		EqualsVerifier.simple().forClass(AttackDamageProfile.class).verify();
		
	}

}
