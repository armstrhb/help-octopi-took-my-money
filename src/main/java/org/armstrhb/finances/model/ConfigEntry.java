package org.armstrhb.finances.model;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConfigEntry {
	public static final int INVALID_INT_VALUE = -1;
	private static final Logger log = LoggerFactory.getLogger(ConfigEntry.class);
	private String key;
	private String value;
	private Date created;
	private Date updated;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = trimIfNotNull(key);
	}
	
	public String getValue() {
		return value;
	}
	
	public int getIntValue() {
		try {
			return Integer.parseInt(getValue());
		} catch (NumberFormatException nfe) {
			log.error("failed to parse number from config value '" + getValue() + "'", nfe);
			
			return INVALID_INT_VALUE;
		}
	}
	
	public void setValue(String value) {
		this.value = trimIfNotNull(value);
	}
	
	private String trimIfNotNull(String inString) {
		return inString != null ? inString.trim() : inString;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getUpdated() {
		return updated;
	}
	
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
