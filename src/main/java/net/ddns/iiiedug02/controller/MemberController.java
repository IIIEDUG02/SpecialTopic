package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonObject;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberInformation;
import net.ddns.iiiedug02.model.service.MemberService;

@Controller
public class MemberController {

  @Autowired
  private MemberService ms;

  // 取得登入成功後使用者名稱
  @GetMapping(value = "/getLoginStatus")
  @ResponseBody
  @Scope("session")
  public String processPrincipalQuery(Principal p, HttpSession session) {
    JsonObject result = new JsonObject();
    // 完成登入後 Principal 才會有物件，若未登入 Principal == null
    Member mb;
    mb = (Member) session.getAttribute("loginBean");

    if (null == p) {
      result.addProperty("username", "null");
    } else {
      if (null == mb) {
        mb = ms.findByUsername(p.getName());
      }
      result.addProperty("username", p.getName());
      session.setAttribute("loginBean", mb);
    }
    return result.toString();

  }

  @PostMapping(value = "/registerAction1",
      produces = "application/x-www-form-urlencoded;charset=UTF-8")
  public String registerAction1(@RequestParam Map<String, String> params, HttpSession session,
      Model m) {

    Member mb = ms.findByUsername(params.get("username"));
    if (null != mb) {
      m.addAttribute("errMsg", "帳號已註冊");
      return "member/registerPage1";
    }

    mb = new Member();
    mb.setUsername(params.get("username"));
    mb.setPassword(params.get("password"));
    mb.setActivated((short) 0);
    session.setAttribute("registerBean", mb);
    return "member/registerPage2";
  }


  @PostMapping(value = "/registerAction2")
  public String registerAction2(@RequestBody MemberInformation mdb, HttpSession session, Model m) {
    Member mb = (Member) session.getAttribute("registerBean");
    mdb.setMember(mb);
    mb.setMemberInformation(mdb);
    ms.createMemberBean(mb);
    return "redirect:/";
  }

  @GetMapping("/countmember.controller")
  @ResponseBody
  public int countMemberAction() {
    return ms.countMember();
  }

  @GetMapping("/signout/success")
  public String signout() {
    return "redirect:/";
  }
}

// @GetMapping("/userprofilesQueryByName.controller")
// public MemberBean processQueryByName() {
// MemberBean uProfiles = ms.findByUsername("nilm987521");
// boolean result = new BCryptPasswordEncoder().matches("abcd1234", uProfiles.getPassword());
// System.out.println("ms result:" + result);
// return uProfiles;
// }


