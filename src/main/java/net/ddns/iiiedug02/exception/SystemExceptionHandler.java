package net.ddns.iiiedug02.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SystemExceptionHandler {

	@ExceptionHandler(NotLoginException.class)
	public ModelAndView noLoginException(NotLoginException e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("errMsg", "請先登入！！");
		return mav;
	}

	@ExceptionHandler(UserNotFoundException.class)
  public Object userNotFountException(UserNotFoundException e) {
    String eMsg = e.getMessage();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eMsg);
	}
	
	
}
