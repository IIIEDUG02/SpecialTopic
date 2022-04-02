package net.ddns.iiiedug02.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * 須從controller拋出Exception，才會觸發必須使用 ModelAndView ，用Model無法傳送訊息
 */
@ControllerAdvice()
public class SystemExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public ModelAndView noLoginException(NotLoginException e, Model m) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errMsg", e.getMessage());
        mav.setViewName("index");
        return mav;
    }

}
