package net.ddns.iiiedug02.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Aspect
@Component
public class TestLog {

	@Around("@annotation(net.ddns.iiiedug02.annotation.LogInfo)")
	public Object logInfo(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String annotatedMethodName = joinPoint.getSignature().getName();

		log.info("----- ClassName:[{}], Function:[{}()]  - > start -----", className, annotatedMethodName);

		int i = 0;
		Object[] args = joinPoint.getArgs();

		for (Object obj : args) {
			i++;
			log.info("{} arg: {}", i, obj.toString());
		}

		Object object = joinPoint.proceed();
		log.info(joinPoint.getSignature().toLongString());
		log.info("----- ClassName:{}, Function:{}  - > end -----", className, annotatedMethodName);
		return object;
	}
}
