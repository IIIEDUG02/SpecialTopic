package net.ddns.iiiedug02.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 放在METHOD上，讓有訂閱的使用者，接收電子郵件
 * 
 * @author nilm
 */
public @interface Subscription {

}
