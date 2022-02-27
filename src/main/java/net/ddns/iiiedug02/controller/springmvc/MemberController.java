package net.ddns.iiiedug02.controller.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.google.gson.JsonObject;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.services.MemberService;



@RestController()
@RequestMapping(value = "member")
@SessionAttributes("loginBean")
public class MemberController {

  @Autowired
  private MemberService ms;

  @RequestMapping(value = "login", produces = "application/json", method = RequestMethod.GET)
  @ResponseBody
  public String loging(Model m, @RequestParam String username, @RequestParam String password) {
    String result = null;
    MemberBean qmb = ms.selectByUsername(username);

    if (qmb == null) {
      result = "User Not Found";
    } else if (!qmb.getPassword().equals(password)) {
      result = "Worng Password";
    } else {
      result = "Sucessed";
      m.addAttribute("loginBean", qmb);
    }

    JsonObject jo = new JsonObject();
    jo.addProperty("result", result);
    return jo.toString();
  }

  @RequestMapping(value = "logout", method = RequestMethod.GET)
  public void logout(SessionStatus status) {
    status.setComplete();
  }

}
