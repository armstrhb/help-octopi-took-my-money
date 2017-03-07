package org.armstrhb.finances.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.armstrhb.finances.dao.mapper.BillMapper;
import org.armstrhb.finances.dao.mapper.ConfigEntryMapper;
import org.armstrhb.finances.dao.mapper.EventMapper;
import org.armstrhb.finances.dao.mapper.PayPeriodMapper;
import org.armstrhb.finances.model.Bill;
import org.armstrhb.finances.model.ConfigEntry;
import org.armstrhb.finances.model.Balance;
import org.armstrhb.finances.model.Event;
import org.armstrhb.finances.model.PayPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class MySQLFinanceDao implements FinanceDao {
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<Bill> getBills() {
		return template.query("select bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill_balance_initial_balance from bill left join bill_balance on bill.bill_id = bill_balance.bill_id", new BillMapper());
	}

	@Override
	public List<Bill> getActiveBills() {
		return template.query("select bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill.bill_type_id, bill_type_name, bill_debt_info_id, bill_debt_info_initial_balance from bill inner join bill_type on bill.bill_type_id = bill_type.bill_type_id left join bill_debt_info on bill.bill_id = bill_debt_info.bill_id where bill_active = true", new BillMapper());
	}

	@Override
	public List<Bill> getInactiveBills() {
		return template.query("select bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill.bill_type_id, bill_type_name, bill_debt_info_id, bill_debt_info_initial_balance from bill inner join bill_type on bill.bill_type_id = bill_type.bill_type_id left join bill_debt_info on bill.bill_id = bill_debt_info.bill_id where bill_active = false", new BillMapper());
	}

	@Override
	public List<Bill> getUpcomingAndDueBills(int upcomingThreshold) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill getBill(int id) {
		return template.queryForObject("select bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill.bill_type_id, bill_type_name, bill_debt_info_id, bill_debt_info_initial_balance from bill inner join bill_type on bill.bill_type_id = bill_type.bill_type_id left join bill_debt_info on bill.bill_id = bill_debt_info.bill_id where bill.bill_id = ?", new BillMapper(), id);
	}

	@Override
	public Bill getBill(String accountNumber) {
		return template.queryForObject("select bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill.bill_type_id, bill_type_name, bill_debt_info_id, bill_debt_info_initial_balance from bill inner join bill_type on bill.bill_type_id = bill_type.bill_type_id left join bill_debt_info on bill.bill_id = bill_debt_info.bill_id where bill_account_number = ?", new BillMapper(), accountNumber);
	}

	@Override
	public Bill getBillByName(String name) {
		return template.queryForObject("select bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill.bill_type_id, bill_type_name, bill_debt_info_id, bill_debt_info_initial_balance from bill inner join bill_type on bill.bill_type_id = bill_type.bill_type_id left join bill_debt_info on bill.bill_id = bill_debt_info.bill_id where bill_name = ?", new BillMapper(), name);
	}

	@Override
	public int createBill(final Bill bill) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into bill (bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plane_amount, bill_day_of_month_due, bill_website, bill_phone_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, bill.getName());
				ps.setString(2, bill.getAccountNumber());
				ps.setTimestamp(3, new Timestamp(new Date().getTime()));
				ps.setTimestamp(4, new Timestamp(new Date().getTime()));
				ps.setBoolean(5, bill.isActive());
				ps.setString(6, bill.getNotes());
				ps.setFloat(7, bill.getPaymentPlanAmount());
				ps.setInt(8,  bill.getDayOfMonthDue());
				ps.setString(9, bill.getWebsite());
				ps.setString(10, bill.getPhoneNumber());
				
				return ps;
			}
		}, keyHolder);
		
		if (bill.hasBalance()) {
			Balance debt = bill.getBalance();
			template.update("insert into bill_balance (bill_id, bill_balance_initial_balance) values (?, ?)", bill.getId(), debt.getInitialBalance()); 
		}
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public void updateBill(Bill bill) {
		template.update("update bill set bill_name = ?, bill_account_number = ?, bill_updated = ?, bill_active = ?, bill_notes = ?, bill_payment_plan_amount = ?, bill_day_of_month_due = ?, bill_website = ?, bill_phone_number = ?, bill.bill_type_id = ? where bill_id = ?", bill.getName(), bill.getAccountNumber(), new Date(), bill.isActive(), bill.getNotes(), bill.getPaymentPlanAmount(), bill.getDayOfMonthDue(), bill.getWebsite(), bill.getPhoneNumber(), bill.getId());
	}

	@Override
	public void deactivateBill(int id) {
		template.update("update bill set bill_active = false where bill_id = ?", id);
	}

	@Override
	public void activateBill(int id) {
		template.update("update bill set bill_active = true where bill_id = ?", id);
	}

	@Override
	public List<PayPeriod> getPayPeriods() {
		return template.query("select pay_period_id, pay_period_start_date, pay_period_end_date, pay_period_begin_amount from pay_period", new PayPeriodMapper());
	}

	@Override
	public List<PayPeriod> getRecentPayPeriods(int num) {
		return template.query("select pay_period_id, pay_period_start_date, pay_period_end_date, pay_period_begin_amount from pay_period order by pay_period_start_date desc limit ?", new PayPeriodMapper(), num);
	}

	@Override
	public PayPeriod getCurrentPayPeriod() {
		return template.queryForObject("select pay_period_id, pay_period_start_date, pay_period_end_date, pay_period_begin_amount from pay_period where now() between pay_period_start_date and pay_period_end_date", new PayPeriodMapper());
	}
	
	@Override
	public PayPeriod getPayPeriod(int id) {
		return template.queryForObject("select pay_period_id, pay_period_start_date, pay_period_end_date, pay_period_begin_amount from pay_period where pay_period_id = ?", new PayPeriodMapper(), id);
	}

	@Override
	public int createPayPeriod(final PayPeriod period) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into pay_period (pay_period_start_date, pay_period_end_date, pay_period_begin_amount) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				ps.setDate(1, new java.sql.Date(period.getStartDate().getTime()));
				ps.setDate(2, new java.sql.Date(period.getEndDate().getTime()));
				ps.setFloat(3, period.getStartingAmount());
				
				return ps;
			}
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public PayPeriod updatePayPeriod(PayPeriod period) {
		template.update("update pay_period set pay_period_start_date = ?, pay_period_end_date = ?, pay_period_begin_amount = ? where pay_period_id = ?", period.getStartDate(), period.getEndDate(), period.getStartingAmount(), period.getId());
		return getPayPeriod(period.getId());
	}

	@Override
	public void deletePayPeriod(PayPeriod period) {
		template.update("delete from pay_period where pay_period_id = ?", period.getId());
	}

	@Override
	public List<Event> getEvents() {
		return template.query("select bill_event_id, bill_event_created, bill_event_updated, bill_event_amount, bill_event_reference, bill_event_actual_due_date, bill_event_due_amount, bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill.bill_type_id, bill_type_name, bill_debt_info_id, bill_debt_info_initial_balance, bill_event_type.bill_event_type_id, bill_event_type.bill_event_type_name, pay_period.pay_period_id, pay_period_start_date, pay_period_end_date, pay_period_begin_amount from bill_event inner join bill on bill_event.bill_id = bill.bill_id inner join bill_type on bill.bill_type_id = bill_type.bill_type_id left join bill_debt_info on bill.bill_id = bill_debt_info.bill_id inner join bill_event_type on bill_event.bill_event_type_id = bill_event_type.bill_event_type_id inner join pay_period on bill_event.pay_period_id = pay_period.pay_period_id", new EventMapper());
	}

	@Override
	public List<Event> getRecentEvents(int num) {
		return template.query("select bill_event_id, bill_event_created, bill_event_updated, bill_event_amount, bill_event_reference, bill_event_actual_due_date, bill_event_due_amount, bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill.bill_type_id, bill_type_name, bill_debt_info_id, bill_debt_info_initial_balance, bill_event_type.bill_event_type_id, bill_event_type.bill_event_type_name, pay_period.pay_period_id, pay_period_start_date, pay_period_end_date, pay_period_begin_amount from bill_event inner join bill on bill_event.bill_id = bill.bill_id inner join bill_type on bill.bill_type_id = bill_type.bill_type_id left join bill_debt_info on bill.bill_id = bill_debt_info.bill_id inner join bill_event_type on bill_event.bill_event_type_id = bill_event_type.bill_event_type_id inner join pay_period on bill_event.pay_period_id = pay_period.pay_period_id order by bill_event_created desc limit ?", new EventMapper(), num);
	}

	@Override
	public List<Event> getEvents(Bill bill) {
		return template.query("select bill_event_id, bill_event_created, bill_event_updated, bill_event_amount, bill_event_reference, bill_event_actual_due_date, bill_event_due_amount, bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill.bill_type_id, bill_type_name, bill_debt_info_id, bill_debt_info_initial_balance, bill_event_type.bill_event_type_id, bill_event_type.bill_event_type_name, pay_period.pay_period_id, pay_period_start_date, pay_period_end_date, pay_period_begin_amount from bill_event inner join bill on bill_event.bill_id = bill.bill_id inner join bill_type on bill.bill_type_id = bill_type.bill_type_id left join bill_debt_info on bill.bill_id = bill_debt_info.bill_id inner join bill_event_type on bill_event.bill_event_type_id = bill_event_type.bill_event_type_id inner join pay_period on bill_event.pay_period_id = pay_period.pay_period_id where bill.bill_id = ?", new EventMapper(), bill.getId());
	}

	@Override
	public List<Event> getEvents(PayPeriod period) {
		return template.query("select bill_event_id, bill_event_created, bill_event_updated, bill_event_amount, bill_event_reference, bill_event_actual_due_date, bill_event_due_amount, bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill.bill_type_id, bill_type_name, bill_debt_info_id, bill_debt_info_initial_balance, bill_event_type.bill_event_type_id, bill_event_type.bill_event_type_name, pay_period.pay_period_id, pay_period_start_date, pay_period_end_date, pay_period_begin_amount from bill_event inner join bill on bill_event.bill_id = bill.bill_id inner join bill_type on bill.bill_type_id = bill_type.bill_type_id left join bill_debt_info on bill.bill_id = bill_debt_info.bill_id inner join bill_event_type on bill_event.bill_event_type_id = bill_event_type.bill_event_type_id inner join pay_period on bill_event.pay_period_id = pay_period.pay_period_id where pay_period.pay_period_id = ?", new EventMapper(), period.getId());
	}

	@Override
	public long createEvent(final Event event) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into bill_event (bill_id, bill_event_created, bill_event_updated, bill_event_amount, bill_event_reference, bill_event_actual_due_date, bill_event_due_amount, pay_period_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, event.getBill().getId());
				ps.setTimestamp(3, new Timestamp(new Date().getTime()));
				ps.setTimestamp(4, new Timestamp(new Date().getTime()));
				ps.setFloat(5, event.getAmountPaid());
				ps.setString(6, event.getReference());
				ps.setDate(7, new java.sql.Date(event.getDueDate().getTime()));
				ps.setFloat(8, event.getAmountDue());
				ps.setInt(9, event.getPayPeriod().getId());
				
				return ps;
			}
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public Event updateEvent(Event event) {
		template.update("update bill_event set bill_id = ?, bill_event_updated = ?, bill_event_amount = ?, bill_event_reference = ?, bill_event_actual_due_date = ?, bill_event_due_amount = ?, pay_period_id = ? where bill_event_id = ?", event.getBill().getId(), new Date(), event.getAmountPaid(), event.getReference(), event.getDueDate(), event.getAmountDue(), event.getPayPeriod().getId());
		return getEvent(event.getId());
	}
	
	@Override
	public Event getEvent(long id) {
		return template.queryForObject("select bill_event_id, bill_event_created, bill_event_updated, bill_event_amount, bill_event_reference, bill_event_actual_due_date, bill_event_due_amount, bill.bill_id, bill_name, bill_account_number, bill_created, bill_updated, bill_active, bill_notes, bill_payment_plan_amount, bill_day_of_month_due, bill_website, bill_phone_number, bill.bill_type_id, bill_type_name, bill_debt_info_id, bill_debt_info_initial_balance, bill_event_type.bill_event_type_id, bill_event_type.bill_event_type_name, pay_period.pay_period_id, pay_period_start_date, pay_period_end_date, pay_period_begin_amount from bill_event inner join bill on bill_event.bill_id = bill.bill_id inner join bill_type on bill.bill_type_id = bill_type.bill_type_id left join bill_debt_info on bill.bill_id = bill_debt_info.bill_id inner join bill_event_type on bill_event.bill_event_type_id = bill_event_type.bill_event_type_id inner join pay_period on bill_event.pay_period_id = pay_period.pay_period_id where bill_event_id = ?", new EventMapper(), id);
	}

	@Override
	public void deleteEvent(Event event) {
		template.update("delete from bill_event where bill_event_id = ?", event.getId());
	}

	@Override
	public List<ConfigEntry> getConfig() {
		return template.query("select config_key, config_value, config_created, config_updated from config", new ConfigEntryMapper());
	}

	@Override
	public ConfigEntry getConfigEntry(String key) {
		return template.queryForObject("select config_key, config_value, config_created, config_updated from config where config_key = ?", new ConfigEntryMapper(), key);
	}

	@Override
	public String createConfigEntry(ConfigEntry entry) {
		template.update("insert into config (config_key, config_value, config_created, config_updated) values (?, ?, ?, ?)", entry.getKey(), entry.getValue(), new Date(), new Date());
		ConfigEntry newEntry = getConfigEntry(entry.getKey());
		
		return newEntry.getKey();
	}

	@Override
	public ConfigEntry updateConfigEntry(ConfigEntry entry) {
		template.update("update config set config_key = ?, config_value = ?, config_updated = ? where config_key = ?", entry.getKey(), entry.getValue(), new Date(), entry.getKey());
		return getConfigEntry(entry.getKey());
	}
	
	@Override
	public ConfigEntry updateConfigKey(ConfigEntry entry, String newKey) {
		template.update("update config set config_key = ?, config_updated = ? where config_key = ?", newKey, new Date(), entry.getKey());
		return getConfigEntry(newKey);
	}

}
