package org.armstrhb.finances.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.armstrhb.finances.model.Event;
import org.armstrhb.finances.model.PayPeriod;
import org.springframework.jdbc.core.RowMapper;

public class EventMapper implements RowMapper<Event> {

	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = new Event();
		
		event.setId(rs.getLong("bill_event_id"));
		event.setCreated(rs.getTimestamp("bill_event_created"));
		event.setUpdated(rs.getTimestamp("bill_event_updated"));
		event.setAmountPaid(rs.getFloat("bill_event_amount"));
		event.setReference(rs.getString("bill_event_reference"));
		event.setDueDate(rs.getDate("bill_event_actual_due_date"));
		event.setAmountDue(rs.getFloat("bill_event_due_amount"));
		event.setPayPeriod(getPayPeriod(rs, rowNum));
		
		return event;
	}
	
	public PayPeriod getPayPeriod(ResultSet rs, int rowNum) throws SQLException {
		PayPeriodMapper mapper = new PayPeriodMapper();
		return mapper.mapRow(rs, rowNum);
	}
	
}
