package net.ddns.iiiedug02.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotLoginException extends RuntimeException {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public NotLoginException() {
    super("No User Login");
  }
}
