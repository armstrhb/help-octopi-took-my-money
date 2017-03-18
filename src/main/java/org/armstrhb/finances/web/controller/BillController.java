package org.armstrhb.finances.web.controller;

import java.util.Map;

import org.armstrhb.finances.model.Bill;
import org.armstrhb.finances.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BillController {
	@Autowired
	BillService billService;
	
	@RequestMapping("/bills")
	public String index(Map<String, Object> model) {
		model.put("bills", billService.getBills());
		return "billindex";
	}
	
	@RequestMapping("/bills/{id}")
	public @ResponseBody Bill getBill(@PathVariable int id) {
		return billService.getBill(id);
	}
}
