package net.ddns.iiiedug02.aop;

import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.MailBean;
import net.ddns.iiiedug02.model.bean.SubscriptionBean;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.SubscriptionService;
import net.ddns.iiiedug02.util.SenedMailUtil;

@Aspect
@Component
public class SubscriptionAop {

    @Autowired
    private SenedMailUtil mailTool;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ClassBeanService classBeanService;

    @Around("execution(* net.ddns.iiiedug02.controller.ClassController.postToOnline(..))")
    public Object Subscription(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println(args[0]);
        boolean result = (boolean) joinPoint.proceed();
        if (result) {
            List<SubscriptionBean> mailList = subscriptionService.findAll();
            if (mailList.isEmpty()) {
                System.out.println("目前沒有任何人訂閱喔");
                return null;
            }
            ClassBean cb = classBeanService.findById((int) args[0]);

            for (SubscriptionBean mail : mailList) {
                MailBean mb = new MailBean();
                mb.setToAddress(mail.getEmail());
                mb.setSubject("新課程上線了～～");
                mb.setMsg("新課程[" + cb.getTitle() + "]]上線了\n"
                        + "https://iiiedug02.nilm.in/SpecialTopic/viewClass/" + args[0]);
                mailTool.send(mb);
            }

        }
        return result;
    }
}
