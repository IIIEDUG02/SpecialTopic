package net.ddns.iiiedug02.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.services.MemberService;

@WebServlet("/LoginAuth")
public class LoginAuth extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public LoginAuth() {
    super();
  }

  private MemberService memberService;

  @Override
  public void init() throws ServletException {
    ServletContext application = getServletContext();
    WebApplicationContext context =
        WebApplicationContextUtils.getWebApplicationContext(application);
    memberService = context.getBean("memberService", MemberService.class);
    super.init();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String jsessionid = "JSESSIONID";

    // 取得Request的username跟password
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // 資料庫物查詢
    MemberBean queryBean = memberService.selectByUsername(username);

    // 建立Session
    HttpSession httpsession = request.getSession(true);
    httpsession.setMaxInactiveInterval(180);// 單位為秒

    // 假如帳號錯誤 memberBean = null
    if (queryBean == null) {
      // 設定Session及ookie
      httpsession.setAttribute("message", "找不到帳號");
      Cookie cookie = new Cookie(jsessionid, httpsession.getId());
      cookie.setMaxAge(30 * 60);
      response.addCookie(cookie);

      // 驗證失敗倒回Login.jsp
      response.sendRedirect("Login.jsp");
      return;
    }

    if (!queryBean.getPassword().equals(password)) {

      // 設定Session及ookie
      httpsession.setAttribute("message", "密碼錯誤");
      Cookie cookie = new Cookie(jsessionid, httpsession.getId());
      cookie.setMaxAge(30 * 60);
      response.addCookie(cookie);

      // 驗證失敗倒回Login.jsp
      response.sendRedirect("Login.jsp");
      return;
    }

    // 設定Session及ookie
    httpsession.setAttribute("loginBean", queryBean);
    Cookie cookie = new Cookie(jsessionid, httpsession.getId());
    cookie.setMaxAge(30 * 60);
    response.addCookie(cookie);

    // 重新指向至Login.jsp，讓Loginfilter引導
    response.sendRedirect("Login.jsp");

  }
}


