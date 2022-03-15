package net.ddns.iiiedug02.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.bean.C2BBean;
import net.ddns.iiiedug02.model.service.CashService;

@Controller
@RequestMapping("cashflow")
public class CashFlowController {

  // private final EntityManagerFactory emf;

  @Autowired
  private CashService cashService;

  // public CashFlowController() {
  // emf = new EntityManagerFactory();
  // }

  @GetMapping(path = "cashFLowListByUid/{uid}")
  @ResponseBody
  public List<C2BBean> cashFLowListByUid(@PathVariable("uid") int uid) {
    return cashService.findByUid(uid);
  }

  @PostMapping(path = "cashFLowTest")
  @ResponseBody
  public C2BBean c2bTest2(@RequestBody C2BBean c2bBean) {
    return c2bBean;
  }


  @GetMapping(path = "yearTop5Class/{year}")
  @ResponseBody
  public List<Map<String, Integer>> getYearTop5Class(@PathVariable("year") int year) {
    return cashService.getYearTop5Class(year);
  }

}
