package net.ddns.iiiedug02.controller.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import net.ddns.iiiedug02.model.beans.MemberBean;

@Controller
public class CashFlowController {


  @GetMapping(path = "tradeRecord")
  public String TradeRecord(HttpSession session, HttpServletRequest request,
      HttpServletResponse response) {
    MemberBean loginBean = (MemberBean) session.getAttribute("loginBean");
    if (null == loginBean) {
      return "index";
    }

    int sid = loginBean.getUid();



    return "123";
  }
}
