package net.ddns.iiiedug02.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * 須從controller拋出Exception，才會觸發，必須使用ModelAndView，用Model無法傳送訊息
 */
@ControllerAdvice()
public class SystemExceptionHandler {

	@ExceptionHandler(NotLoginException.class)
    public ModelAndView noLoginException(NotLoginException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errMsg", e.getMessage());
        mav.setViewName("index");
        return mav;
    }

    @ExceptionHandler(ItemInCartException.class)
    public ModelAndView itemInCartException(ItemInCartException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", e.getMessage());
        mav.setViewName("tradeRecord/shopping_cart_info");
        return mav;
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ModelAndView roleNotFoundException(RoleNotFoundException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errMsg", e.getMessage());
        mav.setViewName("index");
        return mav;
    }
}
