package net.ddns.iiiedug02.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ClassDetailsBean;
import net.ddns.iiiedug02.model.bean.CurriculumBean;
import net.ddns.iiiedug02.model.service.ClassBeanService;

@Controller
@RequestMapping(value = "/update")
public class ClassController2 {

  @Autowired
  private ClassBeanService cbs;

  // 編輯課程
  @GetMapping(value = "/{cid}")
  public ModelAndView updatePage(@PathVariable int cid) {
    ClassBean cb = cbs.findById(cid);
    ModelAndView mav = new ModelAndView();
    mav.addObject("classBean", cb);
    mav.setViewName("../../../WEB-INF/views/backstage/classEdit");
    return mav;
  }

  @PostMapping(value = "/Action")
  public String updateAction(@RequestBody MultiValueMap<String, String> formData) {

    ClassBean cb = new ClassBean();
    cb.setCid(Integer.parseInt(formData.get("cid").get(0)));
    cb.setUid(Integer.parseInt(formData.get("uid").get(0)));
    cb.setClassType(formData.get("classType").get(0));
    cb.setPhoto(new byte[10]);
    cb.setPrice(Integer.parseInt(formData.get("price").get(0)));
    cb.setTitle(formData.get("title").get(0));

    ClassDetailsBean cdb = new ClassDetailsBean();
    cdb.setCid(Integer.parseInt(formData.get("cid").get(0)));
    cdb.setDescript(formData.get("descript").get(0));
    cdb.setLength_min(Integer.parseInt(formData.get("length_min").get(0)));
    cdb.setGoal(formData.get("goal").get(0));
    cdb.setNeed_tool(formData.get("needed_tool").get(0));
    cdb.setStu_required(formData.get("stu_required").get(0));
    cdb.setVideo(formData.get("video").get(0));

    CurriculumBean ccb = new CurriculumBean();
    ccb.setCid(Integer.parseInt(formData.get("cid").get(0)));
    ccb.setCu_title(1);
    ccb.setVideo_path("123");
    ccb.setChapter("123");
    ccb.setClassbean(cb);

    Set<CurriculumBean> ccbSet = new HashSet<CurriculumBean>();
    ccbSet.add(ccb);

    cb.setClassDetailsBean(cdb);
    cb.setCurriculumbean(ccbSet);
    cdb.setClassbean(cb);
    System.out.println(cb);

    // null point exception 作業Update就剩他了
    cbs.update(cb);

    return "redirect:/seeteacherclass";
  }

}
