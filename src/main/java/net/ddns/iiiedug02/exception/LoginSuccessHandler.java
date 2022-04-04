package net.ddns.iiiedug02.exception;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.service.MemberService;

@Component("loginSuccessHandler")
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private MemberService ms;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authentication) throws IOException, ServletException {
        System.out.println("--------- login success --------");
        HttpSession session = request.getSession();
        Principal p = (Principal) authentication.getPrincipal();
        Member loginBean = ms.findByUsername(p.getName());
        session.setAttribute("loginBean", loginBean);
        response.getWriter().print("123456");
    }

}
