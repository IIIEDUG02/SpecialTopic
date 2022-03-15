package net.ddns.iiiedug02.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.YPteacher;
import net.ddns.iiiedug02.model.service.CashService;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.YPteacherService;




@RestController
public class YPteacherController {
	@Autowired
    private YPteacherService bService;
	
	@Autowired
    private CashService cashService;
	
	@Autowired
    private ClassBeanService classService;
	

	
	@GetMapping("/ypteacherquerybyid.controller")
	public YPteacher processQueryByIdAction(Integer id) {
		return bService.findById(id);
	}
	
	@GetMapping("/ypteacherfindAll.controller")
	public List<YPteacher> processfindAll() {
		List<Map<String, Integer>> cList = cashService.getYearTop5Class(2022);
		List<ClassBean> cbList = new ArrayList<ClassBean>();
		for(Map<String, Integer> c : cList) {
			ClassBean cbBean = classService.findById(c.get("cid"));
			cbList.add(cbBean);
		}
		
		
		return bService.findAll();
	}
	
	
	
}
