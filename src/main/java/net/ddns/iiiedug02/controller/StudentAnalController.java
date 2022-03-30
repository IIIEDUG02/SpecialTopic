package net.ddns.iiiedug02.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberInformation;
import net.ddns.iiiedug02.model.bean.StudentAnalysis;

import net.ddns.iiiedug02.model.service.CashService;
import net.ddns.iiiedug02.model.service.ClassBeanService;

import net.ddns.iiiedug02.model.service.StudentAnalService;
import net.ddns.iiiedug02.model.service.YPclassService;

@Controller
public class StudentAnalController {
  @Autowired
  private YPclassService ypclassService;

  @Autowired
  private CashService cashService;

  @Autowired
  private ClassBeanService classBeanService;
  
  @Autowired
  private StudentAnalService analyService;



  @GetMapping("/calAverageAge")
  public String processSaveTop5(Model m) {
	  List<Map<String, Integer>> averageAgeList = cashService.getAverageAge();
	  List<StudentAnalysis> studentAnalList =new  ArrayList<StudentAnalysis>();
	  for(Map<String, Integer> a:averageAgeList) {
		  StudentAnalysis stuanal = new StudentAnalysis();
		  stuanal.setClassID(a.get("cid"));
		  stuanal.setAverageAge(a.get("avgAge"));
		  stuanal.setJob(null);
		  stuanal.setClassbean();
		  analyService.insert(stuanal);
		  
		  studentAnalList.add(stuanal);
		  		  
	  }
	    

    m.addAttribute("studentAnalList", studentAnalList);
    return "Success5";
  }

//  @GetMapping("/ypclassfindtop5")
//  @ResponseBody
//  public List<ClassBean> processFindTop5(Model m) {
//    List<ClassBean> classBeanList = new ArrayList<ClassBean>();
//
//    List<YPclass> YPclassList = ypclassService.findAll();
//    for (YPclass c : YPclassList) {
//    	ClassBean classbean = classBeanService.findById(c.getClassID());
////    	classbean.setClassDetailsBean(null);
//    	classbean.setCurriculumbean(null);
//        
//    	classBeanList.add(classbean);
//    }
//
//    return classBeanList;
//
//  }

//  @GetMapping("/ypclassfindtop5")
//  @ResponseBody
//  public List<ClassBean> processFindTop5(Model m) {
//    List<ClassBean> classBeanList = new ArrayList<ClassBean>();
//
//    List<YPclass> YPclassList = ypclassService.findAll();
//    for (YPclass c : YPclassList) {
//    	ClassBean classBean = c.getClassBean();
//    	classBean.setCurriculumbean(null);
//    	
//      classBeanList.add(classBean);
//    }
//
//    return classBeanList;
//
//  }

}
