package net.ddns.iiiedug02.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * 商品已在購物車
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ItemInCartException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ItemInCartException() {
        super("商品已在購物車");
    }
}
