package org.armstrhb.finances.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BillTest {
	@Test
	public void testAveragePayment() {
		Bill bill = new Bill();
		
		Event event1 = new Event();
		event1.setAmountPaid(50.0f);
		
		Event event2 = new Event();
		event2.setAmountPaid(35.0f);
		
		Event event3 = new Event();
		event3.setAmountPaid(45.0f);
		
		bill.addEvent(event1);
		bill.addEvent(event2);
		bill.addEvent(event3);
		
		assertEquals(42.5f, bill.getAveragePayment(), 0.005);
	}
}
