package org.armstrhb.finances.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.armstrhb.finances.model.PayPeriod;
import org.springframework.jdbc.core.RowMapper;

public class PayPeriodMapper implements RowMapper<PayPeriod> {

	@Override
	public PayPeriod mapRow(ResultSet rs, int rowNum) throws SQLException {
		PayPeriod payPeriod = new PayPeriod();
		
		payPeriod.setId(rs.getInt("pay_period_id"));
		payPeriod.setStartDate(rs.getDate("pay_period_start_date"));
		payPeriod.setEndDate(rs.getDate("pay_period_end_date"));
		payPeriod.setStartingAmount(rs.getFloat("pay_period_begin_amount"));
		
		return payPeriod;
	}

}
