package com.cogpunk.mathhammer;

import org.junit.Before;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class FixedValueTest {

    @Test
	public void testEqualsAndHashCode() {
		
		EqualsVerifier.simple().forClass(FixedValue.class).verify();
		
	}

}
