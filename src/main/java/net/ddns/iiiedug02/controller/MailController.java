package net.ddns.iiiedug02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import net.ddns.iiiedug02.model.bean.MailBean;
import net.ddns.iiiedug02.util.SenedMailUtil;

@Controller
public class MailController {

  @PostMapping("/sendMail")
  public void sendMail(@RequestParam String name, @RequestParam String fromAddress,
      @RequestParam String subject, @RequestParam String msg) {
    SenedMailUtil mu = new SenedMailUtil();
    MailBean mail = new MailBean();
    mail.setFromAddress(fromAddress);
    mail.setMsg(msg);
    mail.setName(name);
    mail.setSubject(subject);
    mu.send(mail);
  }
}
