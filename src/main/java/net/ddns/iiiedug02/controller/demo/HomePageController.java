package net.ddns.iiiedug02.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
  @GetMapping("/")
  public String index() {
    return "index";
  }
}
