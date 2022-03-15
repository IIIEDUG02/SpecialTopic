package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.MemberService;

@Controller
public class ClassController {
	
	@Autowired
	private ClassBeanService cbs;
	
	@Autowired
	private MemberService ms;
	
	//show課程
	@GetMapping(value = "/seeteacherclass")
	public String processShowClass(Principal p,Model m) {
		Member teacher =ms.findByUsername(p.getName());
		List<ClassBean> teacherclass = cbs.findAllById(teacher.getUid());
		m.addAllAttributes(teacherclass);
		
		return "teacherpage";
		
	}
	
	//刪除課程
	@GetMapping(value = "/deleteclass")
	public void deleteClass() {
		
	}
	

}
