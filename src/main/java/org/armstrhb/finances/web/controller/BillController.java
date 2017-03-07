package org.armstrhb.finances.web.controller;

import java.util.Map;

import org.armstrhb.finances.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BillController {
	@Autowired
	BillService billService;
	
	@RequestMapping(value={"", "/"})
	public String index(Map<String, Object> model) {
		model.put("bills", billService.getBills());
		return "index";
	}
}
