package net.ddns.iiiedug02.controller;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping(value = "/registerAction1")
  @ResponseBody
  public String registerAction1(@RequestBody Member mb, HttpSession session) {
    System.out.println(mb);
    session.setAttribute("registerBean2", mb);
    JsonObject result = new JsonObject();
    result.addProperty("result", "ok");
    return result.toString();
  }

  @PostMapping(value = "/registerAction2")
  @ResponseBody
  public String registerAction2(@RequestBody MemberInformation mdb, HttpSession session) {
    Member mb = (Member) session.getAttribute("registerBean2");
    if (null == mb) {
      return "mb is null";
    }
    mdb.setMember(mb);
    mb.setMemberInformation(mdb);
    ms.createMemberBean(mb);
    JsonObject result = new JsonObject();
    result.addProperty("result", "ok");
    return result.toString();
  }

  @GetMapping("/countmember.controller")
  @ResponseBody
  public int countMemberAction() {
    return ms.countMember();
  }

  // @GetMapping("/userprofilesQueryByName.controller")
  // public MemberBean processQueryByName() {
  // MemberBean uProfiles = ms.findByUsername("nilm987521");
  // boolean result = new BCryptPasswordEncoder().matches("abcd1234", uProfiles.getPassword());
  // System.out.println("ms result:" + result);
  // return uProfiles;
  // }


}
