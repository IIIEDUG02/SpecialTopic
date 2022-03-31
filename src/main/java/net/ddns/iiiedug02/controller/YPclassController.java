package net.ddns.iiiedug02.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.YPclass;
import net.ddns.iiiedug02.model.service.CashService;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.YPclassService;



@Controller
public class YPclassController {
  @Autowired
  private YPclassService ypclassService;

  @Autowired
  private CashService cashService;

  @Autowired
  private ClassBeanService classBeanService;

  @Autowired
  private MemberService memberService;


  @GetMapping("/ypclassquerybyid.controller")
  public YPclass processQueryByIdAction(Integer id) {
    return ypclassService.findById(id);
  }

  @GetMapping("/ypclasssavetop5")
  public String processSaveTop5(Model m) {
    List<Map<String, Integer>> cList = cashService.getYearTop5Class(2022);

    List<YPclass> ypclassList = new ArrayList<YPclass>();
    for (Map<String, Integer> c : cList) {
      YPclass ypclass = new YPclass();
      ypclass.setClassID(c.get("cid"));
      ypclass.setYear(2022);
      ypclass.setYearAmount(c.get("countcid"));
      ypclassService.insert(ypclass);


      ypclassList.add(ypclass);
    }

    m.addAttribute("ypclassList", ypclassList);
    return "Success3";
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

  @GetMapping("/ypclassfindtop5")
  @ResponseBody
  public ResponseEntity<List<Map<String, Object>>> processFindTop5(Model m) {

    List<YPclass> YPclassList = ypclassService.findAll();

    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

    for (YPclass c : YPclassList) {
      Map<String, Object> classTeacherInfo = new HashMap<String, Object>();
      ClassBean classBean = c.getClassBean();
      Member teacher = memberService.findByUid(classBean.getUid());

      classBean.setCurriculumbean(null);

      classTeacherInfo.put("class", classBean);
      classTeacherInfo.put("teacher", teacher.getMemberInformation());
      result.add(classTeacherInfo);
    }

    return ResponseEntity.ok(result);

  }

}
