package net.ddns.iiiedug02.controller.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.daos.C2BDao;


@Controller
public class C2BTest {

  @Autowired
  private C2BDao c2bdao;

  @GetMapping(path = "c2b")
  @ResponseBody
  public String c2bTest() {
    return c2bdao.selectBySid(1).toString();
  }


}
