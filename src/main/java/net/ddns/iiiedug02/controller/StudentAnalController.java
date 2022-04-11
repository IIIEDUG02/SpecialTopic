package net.ddns.iiiedug02.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.StudentAnalysis;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.StudentAnalService;

@Controller
public class StudentAnalController {

    @Autowired
    private ClassBeanService classBeanService;

    @Autowired
    private StudentAnalService analyService;

  
  
  @Autowired
  private ClassManagementService classMService;
  
  @RequestMapping(path = "/inputmain", method = RequestMethod.GET)
	public String processMainAction() {
		return "success/MainPercentInput";
	}

  @GetMapping("/getgenderbyID")
  public String getGenderByID(@RequestParam("id") String id, Model m) {
    List<Map<String, Integer>> GenderList = analyService.getgenderbyID(id);
    
    Map<String, String> errors = new HashMap<String, String>();
	m.addAttribute("errors", errors);
	if(id==null || id.length()==0) {
		errors.put("genderid", "請輸入課程ID");
	}
	if(errors!=null && !errors.isEmpty()) {
		return "success/MainPercentInput";
	}
	
    
    if(GenderList!=null && !GenderList.isEmpty()) {
    	m.addAttribute("genderList", GenderList);
		return "success/Success6";
	}
    errors.put("gendermsg", "訂單中無此課程");
	return "success/MainPercentInput";
  }
  
  @GetMapping("/getAgePercentbyID")
  public String getAgePercentByID(@RequestParam("id") String id, Model m) {
    List<Map<String, Integer>> agePercentList = analyService.getAgePercentbyID(id);
    
    Map<String, String> errors = new HashMap<String, String>();
	m.addAttribute("errors", errors);
	if(id==null || id.length()==0) {
		errors.put("ageid", "請輸入課程ID");
	}
	if(errors!=null && !errors.isEmpty()) {
		return "success/MainPercentInput";
	}
	
    
    if(agePercentList!=null && !agePercentList.isEmpty()) {
    	m.addAttribute("agePercentList", agePercentList);
		return "success/Success7";
	}
    errors.put("agemsg", "訂單中無此課程");
	return "success/MainPercentInput";
  }
  
  @GetMapping("/getJobPercentbyID")
  public String getJobPercentByID(@RequestParam("id") String id, Model m) {
    List<Map<String, Integer>> jobPercentList = analyService.getJobPercentbyID(id);
    
    Map<String, String> errors = new HashMap<String, String>();
	m.addAttribute("errors", errors);
	if(id==null || id.length()==0) {
		errors.put("jobid", "請輸入課程ID");
	}
	if(errors!=null && !errors.isEmpty()) {
		return "success/MainPercentInput";
	}
	
    
    if(jobPercentList!=null && !jobPercentList.isEmpty()) {
    	m.addAttribute("jobPercentList", jobPercentList);
		return "success/Success8";
	}
    errors.put("jobmsg", "訂單中無此課程");
	return "success/MainPercentInput";
  }
  
  @GetMapping("/getMoney")
  public String getMoney(Model m) {
    List<Map<String, Integer>> moneyList2022 = analyService.getMoney();
    
	m.addAttribute("moneyList2022", moneyList2022);
	    
	return "success/MoneyPage";
  }
  
  @GetMapping("/getMostJob")
  public String mostjob(Model m) {
    List<Map<String, Integer>> mostjobList = analyService.mostjob();
    
    
	m.addAttribute("mostjobList", mostjobList);
	
    
	return "success/All";
  }
  
  
  
  

}


//@GetMapping("/ypclassfindtop5")
// @ResponseBody
// public List<ClassBean> processFindTop5(Model m) {
// List<ClassBean> classBeanList = new ArrayList<ClassBean>();
//
// List<YPclass> YPclassList = ypclassService.findAll();
// for (YPclass c : YPclassList) {
// ClassBean classbean = classBeanService.findById(c.getClassID());
//// classbean.setClassDetailsBean(null);
// classbean.setCurriculumbean(null);
//
// classBeanList.add(classbean);
// }
//
// return classBeanList;
//
// }

// @GetMapping("/ypclassfindtop5")
// @ResponseBody
// public List<ClassBean> processFindTop5(Model m) {
// List<ClassBean> classBeanList = new ArrayList<ClassBean>();
//
// List<YPclass> YPclassList = ypclassService.findAll();
// for (YPclass c : YPclassList) {
// ClassBean classBean = c.getClassBean();
// classBean.setCurriculumbean(null);
//
// classBeanList.add(classBean);
// }
//
// return classBeanList;
//
// }
//@GetMapping("/calAverageAge")
//public String processSaveTop5(Model m) {
//  List<Map<String, Integer>> averageAgeList = classMService.getAverageAge();
//  List<StudentAnalysis> studentAnalList = new ArrayList<StudentAnalysis>();
//
//  for (Map<String, Integer> a : averageAgeList) {
//    StudentAnalysis stuanal = new StudentAnalysis();
//
//    ClassBean cb = classBeanService.findById(a.get("cid"));
//    stuanal.setAverageAge(a.get("avgAge"));
//    stuanal.setClasstitle(cb.getTitle());
//    stuanal.setJob(null);
//    
//    stuanal.setClassBean(cb);
//    analyService.insert(stuanal);
//
//    studentAnalList.add(stuanal);
//
//  }
//
//
//  m.addAttribute("studentAnalList", studentAnalList);
//  return "success/Success5";
//}