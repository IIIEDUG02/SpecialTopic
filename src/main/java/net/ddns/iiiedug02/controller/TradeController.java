package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class TradeController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private CashService cashService;

  @Autowired
  private ClassBeanService classService;



  @GetMapping("tradeRecord/teacher")
  public String tradeRecordTeacher(Principal p, Model m) {
    if (null == p) {
      throw new NotLoginException();
    }
    Member mb = memberService.findByUsername(p.getName());

    List<ClassBean> classList = classService.findAllByUid(mb.getUid());
    Map<ClassBean, List<C2BBean>> class_c2bList_Map = new HashMap<ClassBean, List<C2BBean>>();

    for (ClassBean classBean : classList) {
      List<C2BBean> tradeList = cashService.findByCid(classBean.getCid());
      class_c2bList_Map.put(classBean, tradeList);
    }

    m.addAttribute("class_c2bList_Map", class_c2bList_Map);
    return "tradeRecord/teacher";
  }

  @GetMapping("tradeRecord/student")
  public ModelAndView tradeRecordStudent(Principal p) {
    if (null == p) {
      throw new NotLoginException();
    }
    Member mb = memberService.findByUsername(p.getName());

    List<C2BBean> c2bl = cashService.findByUid(mb.getUid());

    Map<Date, ClassBean> orderDate_class_map = new HashMap<Date, ClassBean>();
    for (C2BBean c2b : c2bl) {
      ClassBean cb = classService.findById(c2b.getCid());
      orderDate_class_map.put(c2b.getOrderDate(), cb);
    }

    ModelAndView mav = new ModelAndView();
    mav.addObject("orderDate_class_map", orderDate_class_map);
    mav.setViewName("tradeRecord/student");
    return mav;
  }
}
