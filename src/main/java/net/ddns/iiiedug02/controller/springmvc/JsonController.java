package net.ddns.iiiedug02.controller.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.services.MemberService;

@Controller
public class JsonController {

  @Autowired
  private MemberService memberService;

  @RequestMapping(path = "*.json", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
  @ResponseBody
  public String jssonResponse() {
    MemberBean mb = memberService.selectByUsername("nilm");
    // return "你好喔";
    return new Gson().toJson(mb);
  }

  @RequestMapping(path = "error403", method = RequestMethod.GET)
  public ResponseEntity<String> processResponseEntityAction() {
    return new ResponseEntity<>("403 Forbidden", HttpStatus.FORBIDDEN);
  }
}
