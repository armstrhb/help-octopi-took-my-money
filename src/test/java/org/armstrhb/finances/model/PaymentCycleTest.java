package org.armstrhb.finances.model;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class PaymentCycleTest {
	@Test
	public void testBehavior() {
		Calendar calendar = Calendar.getInstance();
		PaymentCycle cycle = new PaymentCycle(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
		assertTrue("current time is present cycle", cycle.isPresent());
		assertEquals("current time is not a past cycle", cycle.isBefore(), false);
		assertEquals("current time is not a future cycle", cycle.isAfter(), false);
		
		cycle = new PaymentCycle(calendar.get(Calendar.MONTH) - 2, calendar.get(Calendar.YEAR));
		assertEquals("past time is a past cycle", cycle.isPresent(), false);
		assertEquals("past time is a past cycle", cycle.isBefore(), true);
		assertEquals("past time is not a future cycle", cycle.isAfter(), false);
		
		cycle = new PaymentCycle(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR) + 1);
		assertEquals("future time is a future cycle", cycle.isPresent(), false);
		assertEquals("future time is not a past cycle", cycle.isBefore(), false);
		assertEquals("future time is a future cycle", cycle.isAfter(), true);		
	}
}
