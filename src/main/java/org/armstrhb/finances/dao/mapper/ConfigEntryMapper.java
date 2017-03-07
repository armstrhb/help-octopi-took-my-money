package org.armstrhb.finances.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.armstrhb.finances.model.ConfigEntry;
import org.springframework.jdbc.core.RowMapper;

public class ConfigEntryMapper implements RowMapper<ConfigEntry> {

	@Override
	public ConfigEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConfigEntry entry = new ConfigEntry();
		
		entry.setKey(rs.getString("config_key"));
		entry.setValue(rs.getString("config_value"));
		entry.setCreated(rs.getTimestamp("config_created"));
		entry.setUpdated(rs.getTimestamp("config_updated"));
		
		return entry;
	}

}
