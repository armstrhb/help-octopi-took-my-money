package org.armstrhb.finances.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.armstrhb.finances.model.Balance;
import org.springframework.jdbc.core.RowMapper;

public class BalanceMapper implements RowMapper<Balance> {

	@Override
	public Balance mapRow(ResultSet rs, int rowNum) throws SQLException {
		Balance balance = new Balance();
		
		balance.setBalanceId(rs.getInt("bill_balance_id"));
		balance.setInitialBalance(rs.getFloat("bill_balance_initial_balance"));
		
		return balance;
	}

}
