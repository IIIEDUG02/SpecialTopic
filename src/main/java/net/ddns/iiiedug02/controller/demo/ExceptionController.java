package net.ddns.iiiedug02.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

  @GetMapping(path = "exceptionAction")
  public void exceptionAction() throws Exception {
    throw new Exception();
  }
}
