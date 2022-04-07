package net.ddns.iiiedug02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import net.ddns.iiiedug02.exception.NotLoginException;

@Controller
public class ExceptionController {

    @GetMapping("testEorrot")
    public String testEorrot() {
        throw new NotLoginException();
    }
}
