package net.ddns.iiiedug02.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ClassDetailsBean;
import net.ddns.iiiedug02.model.bean.MPclass;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberInformation;
import net.ddns.iiiedug02.model.bean.YPclass;
import net.ddns.iiiedug02.model.service.CashService;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassDetailsService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.MPclassService;
import net.ddns.iiiedug02.model.service.YPclassService;




@Controller
public class MPclassController {
  @Autowired
  private MPclassService mpclassService;

  @Autowired
  private CashService cashService;
  
  @Autowired
  private ClassManagementService classMService;


  @GetMapping("/mpclassquerybyid.controller")
  public MPclass processQueryByIdAction(Integer id) {
    return mpclassService.findById(id);
  }

  @GetMapping("/mpclasssavetop5")
  public String processSaveTop5(Model m) {
    List<Map<String, Integer>> cList = classMService.getMonthTop5Class(2022, 3);

    List<MPclass> mpclassList = new ArrayList<MPclass>();
    for (Map<String, Integer> c : cList) {
      MPclass mpclass = new MPclass();
      mpclass.setClassID(c.get("cid"));
      mpclass.setMonth(2022);
      mpclass.setMonthAmount(c.get("countcid"));
      mpclassService.insert(mpclass);


      mpclassList.add(mpclass);
    }

    m.addAttribute("mpclassList", mpclassList);
    return "success/Success4";
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
  
  @GetMapping("/mpclassfindtop5")
  @ResponseBody
  public List<ClassBean> processFindTop5(Model m) {
    List<ClassBean> classBeanList = new ArrayList<ClassBean>();

    List<MPclass> mpclassList = mpclassService.findAll();
    for (MPclass c : mpclassList) {
    	ClassBean classBean = c.getClassBean();
    	classBean.setCurriculumbean(null);
    	
      classBeanList.add(classBean);
    }

    return classBeanList;

  }

}
