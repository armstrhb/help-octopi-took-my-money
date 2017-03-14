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
		
		cycle = PaymentCycle.fromDate(calendar.getTime());
		assertEquals("built cycle must match current month", cycle.getMonth(), calendar.get(Calendar.MONTH) + 1);
		assertEquals("built cycle must match current year", cycle.getYear(), calendar.get(Calendar.YEAR));
	}
	
	@Test
	public void testMonthYearBoundary() {
		PaymentCycle cycle = new PaymentCycle(12, 2016);
		
		cycle.add(1);
		assertEquals("adding one month to december should move month to january", 1, cycle.getMonth());
		assertEquals("adding one month to december should increase year", 2017, cycle.getYear());
		
		cycle.add(-1);
		assertEquals("adding negative months subtracts", 12, cycle.getMonth());
		assertEquals("adding negative months on boundary descreases year", 2016, cycle.getYear());
	}
	
	@Test
	public void testGetNextCycle() {
		PaymentCycle cycle = new PaymentCycle(1, 2017);
		PaymentCycle newCycle = cycle.getNext();
		
		assertEquals("next cycle contains next month", cycle.getMonth() + 1, newCycle.getMonth());
		assertEquals("next cycle contains same year (in this case)", cycle.getYear(), newCycle.getYear());
		assertTrue("origin cycle is unchanged", cycle.getMonth() == 1 && cycle.getYear() == 2017);
	}
}
