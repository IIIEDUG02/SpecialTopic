package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import net.ddns.iiiedug02.exception.NotLoginException;
import net.ddns.iiiedug02.model.bean.C2BBean;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.service.CashService;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.MemberService;

@Controller
public class CashFlowController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private CashService cashService;

  @Autowired
  private ClassBeanService classService;

  @GetMapping("tradeRecord/teacher")
  public ModelAndView tradeRecordTeacher(Principal p) {
    if (null == p) {
      throw new NotLoginException();
    }
    Member mb = memberService.findByUsername(p.getName());

    List<ClassBean> classList = classService.findAllByUid(mb.getUid());
    Map<String, List<C2BBean>> cid_c2bList_Map = new HashMap<String, List<C2BBean>>();

    for (ClassBean classBean : classList) {
      List<C2BBean> tradeList = cashService.findByCid(classBean.getCid());
      cid_c2bList_Map.put(classBean.getTitle(), tradeList);
    }

    ModelAndView mav = new ModelAndView();
    mav.addObject("cid_c2bList_Map", cid_c2bList_Map);
    mav.setViewName("../../../WEB-INF/views/tradeRecord/teacher");
    return mav;

  }

}
