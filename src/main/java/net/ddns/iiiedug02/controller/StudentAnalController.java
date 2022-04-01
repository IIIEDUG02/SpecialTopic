package net.ddns.iiiedug02.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.StudentAnalysis;
import net.ddns.iiiedug02.model.service.CashService;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.StudentAnalService;

@Controller
public class StudentAnalController {

  @Autowired
  private CashService cashService;

  @Autowired
  private ClassBeanService classBeanService;

  @Autowired
  private StudentAnalService analyService;
  
  @Autowired
  private ClassManagementService classMService;

  @GetMapping("/calAverageAge")
  public String processSaveTop5(Model m) {
    List<Map<String, Integer>> averageAgeList = classMService.getAverageAge();
    List<StudentAnalysis> studentAnalList = new ArrayList<StudentAnalysis>();

    for (Map<String, Integer> a : averageAgeList) {
      StudentAnalysis stuanal = new StudentAnalysis();

      ClassBean cb = classBeanService.findById(a.get("cid"));
      stuanal.setAverageAge(a.get("avgAge"));
      stuanal.setClasstitle(cb.getTitle());
      stuanal.setJob(null);
      
      stuanal.setClassBean(cb);
      analyService.insert(stuanal);

      studentAnalList.add(stuanal);

    }


    m.addAttribute("studentAnalList", studentAnalList);
    return "success/Success5";
  }

  // @GetMapping("/ypclassfindtop5")
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

}
