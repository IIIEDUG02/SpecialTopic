package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.ddns.iiiedug02.helpers.ArticleHelper;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.service.ClassBeanService;

@Controller
@RequestMapping(path = "/instructor") // admin
public class TeacherController {
	
	  @Autowired
	  private ClassBeanService cbs;
	
	// Singleton pattern(單例模式): 保證物件只會 new 一次(不會有多個物件)
	private ArticleHelper articleHelper = ArticleHelper.getInstance();
	private String ROLE = "ROLE_admin";
	
	@GetMapping("")
	public String dispatch(HttpServletRequest request, Principal principal, Model model) {
		// 進入頁面需admin認證
		if (!articleHelper.hasRole(principal, ROLE)) {
			return "member/loginPage";
		}
		List<ClassBean> cbList = cbs.findAll();
		
		model.addAttribute("classes",cbList);
		
		return "backstage/teacherpage";
	}
	
	
	
}