package net.ddns.iiiedug02.controller.springmvc;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.JsonObject;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.beans.MemberDetailBean;
import net.ddns.iiiedug02.model.services.MemberService;
import net.ddns.iiiedug02.utils.SqlDateUtil;

@Controller()
public class MemberController {

  @Autowired
  private MemberService ms;

  @Autowired
  private SqlDateUtil sqlDateUtil;

  @RequestMapping(method = RequestMethod.GET)
  public String loginIndex() {
    return "login";
  }

  // 登入
  @RequestMapping(value = "login", method = RequestMethod.POST)
  public ModelAndView loging(Model m, @RequestParam String username, @RequestParam String password,
      HttpServletResponse response, HttpServletRequest request) {
    Map<String, String> err = new HashMap<String, String>();
    ModelAndView mav = new ModelAndView();

    if (username == null || username.length() == 0) {
      err.put("username", "請輸入帳號");
    }

    if (password == null || password.length() == 0) {
      err.put("password", "請輸入密碼");
    }

    if (!err.isEmpty()) {
      mav.addObject("err", err);
      mav.setViewName("login");
      return mav;
    } else {
      MemberBean qmb = ms.selectByUsername(username);
      if (qmb == null) {
        err.put("result", "帳號不存在");
        mav.addObject("err", err);
        return mav;
      } else if (!qmb.getPassword().equals(password)) {
        err.put("result", "密碼不對");
        System.out.println("輸入：" + password);
        System.out.println("資料庫：" + qmb.getPassword());
        mav.addObject("err", err);
        return mav;
      } else {
        m.addAttribute("loginBean", qmb);
        Cookie cookie = new Cookie("token", qmb.getToken());
        Cookie jsession = new Cookie("JSESSIONID", request.getSession().getId());
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        response.addCookie(jsession);
      }

      mav.addObject("username", qmb.getUsername());
      mav.setViewName("sucess");
      return mav;
    }
  }

  // 登出
  @RequestMapping(value = "logout", method = RequestMethod.GET)
  public String logout(SessionStatus status) {
    status.setComplete();
    return "";
  }

  // 註冊 "result" : "Sucesse" / "Fail"
  @RequestMapping(method = RequestMethod.POST, value = "signup")
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

}
