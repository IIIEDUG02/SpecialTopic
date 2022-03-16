package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ddns.iiiedug02.exception.NotLoginException;
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

  // show課程
  @GetMapping(value = "/seeteacherclass")
  public String processShowClass(Principal p, Model m) {
    if (null == p) {
      throw new NotLoginException();
    }
    Member teacher = ms.findByUsername(p.getName());
    List<ClassBean> teacherclass = cbs.findAllByUid(teacher.getUid());
    m.addAttribute("teacherclass", teacherclass);

    return "teacherpage";

  }

  // 刪除課程
  @GetMapping(value = "/deleteclass/{cid}")
  public void deleteClass(@PathVariable int cid) {
    cbs.deleteById(cid);
  }
  
  //不同課程類型總數
  @ResponseBody
  @GetMapping(value = "/countclass")
  public int processCountClass() {
	  return cbs.countClass();
  }
  
  //尋找全部課程類型
//  @ResponseBody
//  @GetMapping(value = "/findallclasstype")
//  public String processFindAllClassType() {
//	  List<ClassBean> cList = cbs.findAllClassType();
//	  for(ClassBean c:cList) {
//		  Set<ClassBean> cSet.add
//	  }
//  }
  
  
  


}
