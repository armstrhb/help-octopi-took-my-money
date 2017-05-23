package org.armstrhb.finances.service;

import java.util.List;

import org.armstrhb.finances.dao.FinanceDao;
import org.armstrhb.finances.model.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillService {
	private static final Logger log = LoggerFactory.getLogger(BillService.class);
	
	@Autowired
	FinanceDao dao;
	
	public List<Bill> getBills() {
		log.debug("retrieving bills");
		return dao.getBills();
	}
	
	public Bill getBill(int id) {
		log.debug("retrieving bill detail for bill id " + id);
		return dao.getBill(id);
	}
	
	public boolean createBill(Bill bill) {
		log.info("creating bill '" + bill.getName() + "'");
		boolean result = false;
		
		try {
			int id = dao.createBill(bill);
			
			if (id > 0) {
				result = true;
				log.info("bill '" + bill.getName() + "' created, id: " + id);
			}
		} catch (Exception e) {
			log.error("error encountered while trying to save bill", e);
		}
		
		return result;
	}
}
