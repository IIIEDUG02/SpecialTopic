package net.ddns.iiiedug02.controller.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.util.SqlDateUtil;


// @CrossOrigin(allowCredentials = "true", origins = {"*"})
@RestController()
@RequestMapping(value = "memberApi")
@SessionAttributes("loginBean")
public class MemberApi {

  @Autowired
  private MemberService ms;

  @Autowired
  private SqlDateUtil sqlDateUtil;

  // // 檢查登入狀態 "result" : "null" / $userName
  // @RequestMapping(produces = "application/json", method = RequestMethod.GET)
  // public String status(HttpServletRequest request, Model m) {
  // MemberBean loginBean = (MemberBean) (m.getAttribute("loginBean"));
  // Cookie[] cookies = request.getCookies();
  // String token = null;
  //
  // if (cookies != null) {
  // for (Cookie c : cookies) {
  // if (c.getName().equals("token")) {
  // token = c.getValue();
  // }
  // }
  // }
  //
  // JsonObject jo = new JsonObject();
  // MemberBean qmb;
  // if (loginBean != null) {
  // jo.addProperty("result", loginBean.getUsername());
  // } else if (token == null) {
  // jo.addProperty("result", "null");
  // } else if ((qmb = ms.selectByToken(token)) != null) {
  // jo.addProperty("result", qmb.getUsername());
  // } else {
  // jo.addProperty("result", "null");
  // }
  // return jo.toString();
  // }

  // 登入 "result" : "User Not Found" / "Worng Password" / "Sucesse"
  // @RequestMapping(value = "login", produces = "application/json", method = RequestMethod.POST)
  // @ResponseBody
  // public String loging(Model m, @RequestParam String username, @RequestParam String password,
  // HttpServletResponse response, HttpServletRequest request) {
  // String result = null;
  // MemberBean qmb = ms.selectByUsername(username);
  //
  // if (qmb == null) {
  // result = "User Not Found";
  // } else if (!qmb.getPassword().equals(password)) {
  // result = "Worng Password";
  // } else {
  // result = "Sucesse";
  // m.addAttribute("loginBean", qmb);
  // // Cookie cookie = new Cookie("token", qmb.getToken());
  // Cookie jsession = new Cookie("JSESSIONID", request.getSession().getId());
  // // jsession.setPath("http://localhost:5500/");
  // // cookie.setMaxAge(60 * 60 * 24);
  // // response.addCookie(cookie);
  // response.addCookie(jsession);
  // }
  //
  // JsonObject jo = new JsonObject();jo.addProperty("result",result);return jo.toString();
  // }

  // 登出
  @RequestMapping(value = "logout", method = RequestMethod.GET)
  public void logout(SessionStatus status) {
    status.setComplete();
  }

  // // 註冊 "result" : "Sucesse" / "Fail"
  // @RequestMapping(method = RequestMethod.POST, value = "signup")
  // @ResponseBody
  // public String signUp(@RequestParam String username, @RequestParam String password,
  // @RequestParam String fullname, @RequestParam String email, @RequestParam String birthday,
  // @RequestParam String address, @RequestParam String phone, @RequestParam String job) {
  //
  // MemberBean mb = new MemberBean();
  // MemberDetailBean mdb = new MemberDetailBean();
  //
  // mb.setUsername(username).setPassword(password).setRoles("admin").setMemberDetail(mdb);
  // mdb.setAddress(address).setBirthday(sqlDateUtil.strToDate(birthday)).setEmail(email)
  // .setFullname(fullname).setJob(job).setPhone(phone).setMember(mb);
  //
  // JsonObject jo = new JsonObject();
  //
  // if (ms.addMember(mb)) {
  // jo.addProperty("result", "Sucesse");
  // } else {
  // jo.addProperty("result", "Fail");
  // }
  // return jo.toString();
  // }

}
