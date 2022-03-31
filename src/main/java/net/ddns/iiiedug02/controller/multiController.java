package net.ddns.iiiedug02.controller;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ClassManagementBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.ShoppingCart;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.ShoppingCartService;

@SessionAttributes("loginBean")
@Controller
public class multiController {

  @Autowired
  private ClassBeanService cbs;

  @Autowired
  private ShoppingCartService scs;

  @Autowired
  private ClassManagementService cms;

  @GetMapping("viewClass/{cid}")
  @Scope("session")
  public String viewClass(@PathVariable("cid") int cid, Model m, Principal p,
      HttpSession httpsession, @SessionAttribute("loginBean") Member loginBean) {

    // 課程資訊
    ClassBean cb = cbs.findById(cid);
    if (cb == null) {
      m.addAttribute("errMsg", "找不到課程資料");
      return "class/viewClass";
    }
    m.addAttribute("classBean", cb);


    // 個人購課紀錄
    ClassManagementBean cmb = cms.findByUidAndCid(loginBean.getUid(), cid);
    if (null != cmb) {
      m.addAttribute("classManagerBean", cmb);
    } else {
      // 購課車
      ShoppingCart sc = scs.findByUidAndClassBean(loginBean.getUid(), cb);
      m.addAttribute("ShoppingCart", sc);
    }



    // 留言板


    return "class/viewClass";
  }
}
