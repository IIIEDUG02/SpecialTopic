package net.ddns.iiiedug02.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClassNotFoundException extends RuntimeException {

    /**
     * 回傳找不到課程
     */
    private static final long serialVersionUID = 1L;

    public ClassNotFoundException() {
        super("找不到課程");
    }

}
