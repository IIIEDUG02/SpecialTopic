package net.ddns.iiiedug02.controller.springmvc;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.JsonObject;
import net.ddns.iiiedug02.model.beans.Mbs;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.beans.MemberDetailBean;
import net.ddns.iiiedug02.model.services.MemberService;
import net.ddns.iiiedug02.utils.SqlDateUtil;

@Controller
public class MemberController {

  @Autowired
  private MemberService ms;

  @Autowired
  private SqlDateUtil sqlDateUtil;

  // 首頁 "/"
  @RequestMapping(method = RequestMethod.GET)
  public String index(HttpSession session, Model m) {
    MemberBean loginBean = (MemberBean) session.getAttribute("loginBean");
    if (null != loginBean)
      m.addAttribute("username", loginBean.getUsername());
    return "index";
  }

  // 登入 "/login"
  @RequestMapping(value = "login", method = RequestMethod.POST)
  public ModelAndView loging(Model m, @RequestParam String username, @RequestParam String password,
      HttpServletResponse response, HttpServletRequest request, HttpSession session) {

    ModelAndView mav = new ModelAndView();

    // 帳密驗證
    Map<String, String> err = new HashMap<String, String>();
    if (username == null || username.length() == 0) {
      err.put("username", "請輸入帳號");
    }
    if (password == null || password.length() == 0) {
      err.put("password", "請輸入密碼");
    }
    if (!err.isEmpty()) {
      mav.setViewName("index");
      mav.addObject("err", err);
      return mav;
    }

    MemberBean qmb = ms.selectByUsername(username);
    mav.setViewName("index");
    if (qmb == null) {
      err.put("result", "帳號不存在");
      mav.addObject("err", err);
    } else if (!qmb.getPassword().equals(password)) {
      err.put("result", "密碼不對");
      mav.addObject("err", err);
    } else {
      session.setAttribute("loginBean", qmb);
      mav.addObject("username", qmb.getUsername());
      // Cookie cookie = new Cookie("token", qmb.getToken());
      Cookie jsession = new Cookie("JSESSIONID", request.getSession().getId());
      // cookie.setMaxAge(60 * 60 * 24);
      // response.addCookie(cookie);
      response.addCookie(jsession);
    }
    return mav;
  }

  // 登出 "/logout"
  @RequestMapping(value = "logout", method = RequestMethod.GET)
  public String logout(SessionStatus status) {
    status.setComplete();
    return "index";
  }

  // 註冊 "/register" "result" : "Sucesse" / "Fail"
  @RequestMapping(method = RequestMethod.POST, value = "register")
  @ResponseBody
  public String signUp(@RequestParam String username, @RequestParam String password,
      @RequestParam String fullname, @RequestParam String email, @RequestParam String birthday,
      @RequestParam String address, @RequestParam String phone, @RequestParam String job) {

    MemberBean mb = new MemberBean();
    MemberDetailBean mdb = new MemberDetailBean();

    mb.setUsername(username).setPassword(password).setRoles("admin").setMemberDetail(mdb);
    mdb.setAddress(address).setBirthday(sqlDateUtil.strToDate(birthday)).setEmail(email)
        .setFullname(fullname).setJob(job).setPhone(phone).setMember(mb);

    JsonObject jo = new JsonObject();

    if (ms.addMember(mb)) {
      jo.addProperty("result", "Sucesse");
    } else {
      jo.addProperty("result", "Fail");
    }
    return jo.toString();
  }

  // form:form 修改資料
  @RequestMapping(path = "info", method = RequestMethod.GET)
  public String Info(Model m, @ModelAttribute("loginBean") MemberBean loginBean) {
    if (loginBean == null) {
      return "error";
    }
    return "members";
  }



  // form:form 表單
  @RequestMapping(path = "infoDemo", method = RequestMethod.GET)
  public String InfoDemo(Model m) {
    Mbs mbs = new Mbs("Dennis", "male", 18);
    m.addAttribute("Mbs", mbs);
    return "members";
  }

  @RequestMapping(path = "addMember", method = RequestMethod.POST)
  public String processAction(@ModelAttribute("Mbs") Mbs mbs, BindingResult result, Model m2) {
    if (result.hasErrors()) {
      return "error";
    }
    m2.addAttribute("mName", mbs.getName());
    m2.addAttribute("mGender", mbs.getGender());
    m2.addAttribute("mAge", mbs.getAge());
    return "memberResult";
  }

}
