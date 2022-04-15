package net.ddns.iiiedug02.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * 當username不存在時，拋出錯誤
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("帳號錯誤");
    }

}
