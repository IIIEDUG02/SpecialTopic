package net.ddns.iiiedug02.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Aspect
@Component

/*
 * Log記錄器，使用@AspectLogAnnotation可以將Class、Method名稱及參數記錄到Log裏面，詳細設定請參照 src/main/resource/log4j2.xml
 */
public class AspectLog {

    @Around("@annotation(net.ddns.iiiedug02.annotation.AspectLogAnnotation)")
    public Object logInfo(ProceedingJoinPoint joinPoint) throws Throwable {

        int i = 0;
        Object[] args = joinPoint.getArgs();

        log.info("----- [start] -> {} -----", joinPoint.toString());
        for (Object obj : args) {
            i++;
            log.info("arg-{}: {}", i, obj.toString());
        }
        Object object = joinPoint.proceed();
        log.info("----- [end] -> {} -----", object);
        return object;
    }
}
