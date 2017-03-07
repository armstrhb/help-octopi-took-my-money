package org.armstrhb.finances.dao;

import java.util.List;

import org.armstrhb.finances.model.Bill;
import org.armstrhb.finances.model.ConfigEntry;
import org.armstrhb.finances.model.Event;
import org.armstrhb.finances.model.PayPeriod;

public interface FinanceDao {
	public List<Bill> getBills();
	public List<Bill> getActiveBills();
	public List<Bill> getInactiveBills();
	public List<Bill> getUpcomingAndDueBills(int upcomingThreshold);
	public Bill getBill(int id);
	public Bill getBill(String accountNumber);
	public Bill getBillByName(String name);
	public int createBill(Bill bill);
	public void updateBill(Bill bill);
	public void deactivateBill(int id);
	public void activateBill(int id);
	
	public List<PayPeriod> getPayPeriods();
	public List<PayPeriod> getRecentPayPeriods(int num);
	public PayPeriod getCurrentPayPeriod();
	public PayPeriod getPayPeriod(int id);
	public int createPayPeriod(PayPeriod period);
	public PayPeriod updatePayPeriod(PayPeriod period);
	public void deletePayPeriod(PayPeriod period);
	
	public List<Event> getEvents();
	public List<Event> getRecentEvents(int num);
	public List<Event> getEvents(Bill bill);
	public List<Event> getEvents(PayPeriod period);
	public long createEvent(Event event);
	public Event updateEvent(Event event);
	public Event getEvent(long id);
	public void deleteEvent(Event event);
	
	public List<ConfigEntry> getConfig();
	public ConfigEntry getConfigEntry(String key);
	public String createConfigEntry(ConfigEntry entry);
	public ConfigEntry updateConfigEntry(ConfigEntry entry);
	public ConfigEntry updateConfigKey(ConfigEntry entry, String newKey);
}
