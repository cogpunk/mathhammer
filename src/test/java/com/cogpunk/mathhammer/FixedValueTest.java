package com.cogpunk.mathhammer;

import org.junit.Before;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class FixedValueTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEqualsAndHashCode() {
		
		EqualsVerifier.simple().forClass(FixedValue.class).verify();
		
	}

}
