package org.armstrhb.finances.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.armstrhb.finances.model.Bill;
import org.armstrhb.finances.model.Balance;
import org.springframework.jdbc.core.RowMapper;

public class BillMapper implements RowMapper<Bill> {

	@Override
	public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
		Bill bill = new Bill();
		
		bill.setId(rs.getInt("bill_id"));
		bill.setName(rs.getString("bill_name"));
		bill.setAccountNumber(rs.getString("bill_account_number"));
		bill.setCreated(rs.getTimestamp("bill_created"));
		bill.setUpdated(rs.getTimestamp("bill_updated"));
		bill.setActive(rs.getBoolean("bill_active"));
		bill.setNotes(rs.getString("bill_notes"));
		bill.setDayOfMonthDue(rs.getInt("bill_day_of_month_due"));
		bill.setWebsite(rs.getString("bill_website"));
		bill.setPhoneNumber(rs.getString("bill_phone_number"));
		bill.setPaymentPlanAmount(getPaymentPlanAmount(rs));
		
		if (hasBalance(rs)) {
			bill.setBalance(getBalanceProperties(rs, rowNum));
		}
		
		return bill;
	}
	
	public float getPaymentPlanAmount(ResultSet rs) throws SQLException {
		float amount;
		
		try {
			amount = rs.getFloat("bill_payment_plan_amount");
		} catch (SQLException sqle) {
			if (rs.wasNull()) {
				amount = Bill.NO_PAYMENY_PLAN_AMOUNT;
			} else {
				throw sqle;
			}
		}
		
		return amount;
	}
	
	public Balance getBalanceProperties(ResultSet rs, int rowNum) throws SQLException {
		BalanceMapper mapper = new BalanceMapper();
		return mapper.mapRow(rs, rowNum);
	}
	
	public boolean hasBalance(ResultSet rs) throws SQLException {
		boolean isDebt = false;
		
		try {
			isDebt = rs.getInt("bill_debt_info_id") > 0;
		} catch (SQLException sqle) {
			if (!rs.wasNull()) {
				throw sqle;
			}
		}
		
		return isDebt;
	}

}
