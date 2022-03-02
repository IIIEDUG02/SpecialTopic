package net.ddns.iiiedug02.controller.springmvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HeaderController {

  @RequestMapping(path = "headerSet", method = RequestMethod.GET)
  public ResponseEntity<String> headerSetter() {
    HttpHeaders header = new HttpHeaders();
    header.setContentType(MediaType.TEXT_PLAIN);
    header.set("Set-Cookie", "JSESSIONID=123456");;
    return new ResponseEntity<String>("header:JESSIONID=123456", header, HttpStatus.OK);
  }
}
