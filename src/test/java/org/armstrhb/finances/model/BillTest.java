package org.armstrhb.finances.model;

import static org.junit.Assert.*;

import java.util.Date;

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
	
	@Test
	public void testEventSort() {
		Bill bill = new Bill();
		
		Date now = new Date();
		Date before = new Date(now.getTime() - 5000);
		
		Event event1 = new Event();
		event1.setId(1);
		event1.setCreated(now);
		
		Event event2 = new Event();
		event2.setId(2);
		event2.setCreated(before);
		
		bill.addEvent(event1);
		bill.addEvent(event2);
		
		assertEquals("default sort should be descending on creation stamp", bill.getEvents().get(0).getId(), 1);
		assertEquals("sort by ascending on demand", bill.getEvents(false).get(0).getId(), 2);
	}
}
