package org.armstrhb.finances.model;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class BillTest {
	@Test
	public void testGetDueDate() {
		int dayOfMonthDue = 9;
		
		Bill bill = new Bill();
		bill.setDayOfMonthDue(dayOfMonthDue);
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		calendar.set(Calendar.DATE, dayOfMonthDue);
		
		if (calendar.getTime().before(now)) {
			calendar.add(Calendar.MONTH, 1);
		}
		
		assertEquals("due date when no previous payment is next possible", calendar.getTime(), bill.getDueDate());
		
		Calendar workingDate = Calendar.getInstance();
		workingDate.add(Calendar.MONTH, -2);
		workingDate.set(Calendar.DATE, dayOfMonthDue - 4);
		bill.setLastPaymentDate(workingDate.getTime());
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DATE, dayOfMonthDue);
		
		
		Calendar dueDate = Calendar.getInstance();
		dueDate.setTime(bill.getDueDate());
		
		assertTrue("past due date should be still previous closest date", calendar.get(Calendar.MONTH) == dueDate.get(Calendar.MONTH) && calendar.get(Calendar.DATE) == dueDate.get(Calendar.DATE) && calendar.get(Calendar.YEAR) == dueDate.get(Calendar.YEAR));
	}
}
