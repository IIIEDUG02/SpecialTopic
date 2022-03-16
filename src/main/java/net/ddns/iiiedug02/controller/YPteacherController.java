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
  private YPteacherService bService;

  @Autowired
  private CashService cashService;

  @Autowired
  private ClassBeanService classService;

  @Autowired
  private MemberService memberService;



  @GetMapping("/ypteacherquerybyid.controller")
  public YPteacher processQueryByIdAction(Integer id) {
    return bService.findById(id);
  }

  @GetMapping("/ypteacherfindtop5")
  public String processfindAll(Model m) {
    List<Map<String, Integer>> cList = cashService.getYearTop5Class(2022);

    List<Member> memberList = new ArrayList<Member>();
    for (Map<String, Integer> c : cList) {
      ClassBean cbBean = classService.findById(c.get("cid"));
      Member mb = memberService.findByUid(cbBean.getUid());
      // YPteacher ypth = new YPteacher();
      // ypth.setTeacherID(mb.getUid());
      // ypth.setYear(2022);
      // ypth.setYearAmount(c.get("countcid"));
      // bService.insert(ypth);
      memberList.add(mb);
    }

    m.addAttribute("memberList", memberList);
    return "ypteacherQueryAll";
  }



}
