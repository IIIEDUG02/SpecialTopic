package net.ddns.iiiedug02.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.YPteacher;
import net.ddns.iiiedug02.model.service.CashService;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.YPteacherService;



@Controller
public class YPteacherController {
  @Autowired
  private YPteacherService ypteacherService;

  @Autowired
  private CashService cashService;

  @Autowired
  private ClassBeanService classbeanService;

  @Autowired
  private MemberService memberService;



  @GetMapping("/ypteacherquerybyid.controller")
  public YPteacher processQueryByIdAction(Integer id) {
    return ypteacherService.findById(id);
  }

  @GetMapping("/ypteachersavetop5")
  public String processSaveTop5(Model m) {
    List<Map<String, Integer>> cList = cashService.getYearTop5Class(2022);

//    List<Member> memberList = new ArrayList<Member>();
    for (Map<String, Integer> c : cList) {
      ClassBean cbBean = classbeanService.findById(c.get("cid"));
      Member mb = memberService.findByUid(cbBean.getUid());
       YPteacher ypteacher = new YPteacher();
       ypteacher.setTeacherID(mb.getUid());
       ypteacher.setYear(2022);
       ypteacher.setYearAmount(c.get("countcid"));
       ypteacherService.insert(ypteacher);
      
      
//      memberList.add(mb);
    }

//    m.addAttribute("memberList", memberList);
    return "Success";
  }
  
  @GetMapping("/ypteacherfindtop5")
  public String processFindTop5(Model m) {
	List<YPteacher> YPteacherList = ypteacherService.findAll();
   
	m.addAttribute("YPteacherList", YPteacherList);
      
    return "ypteacherQueryAll";
  }



}
