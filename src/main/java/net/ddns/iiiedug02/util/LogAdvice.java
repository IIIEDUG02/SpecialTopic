package net.ddns.iiiedug02.util;
// package net.ddns.iiiedug02.utils;
//
// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.AfterReturning;
// import org.aspectj.lang.annotation.AfterThrowing;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.annotation.Pointcut;
// import org.springframework.stereotype.Component;
//
// @Component("logAdvice")
// @Aspect
// public class LogAdvice {
//
// // @Pointcut(value = "execution(*
// net.ddns.iiiedug02.model.services.MemberService.selectBy*(..))")
// @Pointcut(value = "execution(* net.ddns.iiiedug02.*(..))")
// private void pointcut1() {
//
// }
//
// @Before(value = "pointcut1()")
// public void logBefore(JoinPoint point) {
// System.out.println("Before at : " + point.getTarget().getClass() + " ");
// System.out.println("Calling : " + point.getSignature().getName() + " ");
// // System.out.println("Using : " + point.getArgs()[0] + " ");
// System.out.println("Before : going into JoinPoint method.");
// }
//
// @Around(value = "pointcut1()")
// public Object logAround(ProceedingJoinPoint pPoint) throws Throwable {
// System.out.println("around : at " + pPoint.getTarget().getClass() + " ");
// System.out.println("around : " + pPoint.getSignature().getName() + " ");
// // System.out.println("using : at " + pPoint.getArgs()[0] + " ");
// Object result = pPoint.proceed();
// System.out.println("around result : " + result);
// return result;
// }
//
// @AfterReturning(value = "pointcut1()", returning = "result")
// public void logAfter(JoinPoint point, Object result) {
// System.out.println("after : JoinPoint method finished");
// System.out.println("after result : " + result);
// }
//
// @AfterThrowing(value = "pointcut1()", throwing = "e")
// public void logThrow(JoinPoint point, Exception e) {
// System.out.println("Exception : " + e);
// }
// }
