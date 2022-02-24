package net.ddns.iiiedug02.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.services.MemberService;
import net.ddns.iiiedug02.utils.MD5Util;

/**
 * 執行登入驗證，並回饋是否帳號密碼錯誤，若驗證成功則藉由LoginFilter，執行頁面跳轉
 */
@WebServlet("/LoginAuth")
public class LoginAuth extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public LoginAuth() {
    super();
  }

  @Autowired
  private MemberService memberService;

  @Autowired
  private MD5Util md5Util;

  private WebApplicationContext springContext;

  @Override
  public void init(final ServletConfig config) throws ServletException {
    super.init(config);
    springContext =
        WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
    final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
    beanFactory.autowireBean(this);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String jsessionid = "JSESSIONID";
    // 取得Request的username跟password
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String autoLogin = request.getParameter("autoLogin");
    HttpSession httpsession;

    try {
      request.login(username, password);
      httpsession = request.getSession(true);
      httpsession.setAttribute("username", request.getRemoteUser());
      response.sendRedirect("/SpecialTopic/admin/MemberList.jsp");
      return;
    } catch (Exception e) {
      // e.printStackTrace();
    }

    // 資料庫物查詢
    MemberBean queryBean = memberService.selectByUsername(username);

    // 建立Session
    httpsession = request.getSession(true);
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
    httpsession.setAttribute("username", request.getRemoteUser());
    Cookie sessionCookie = new Cookie(jsessionid, httpsession.getId());
    Cookie autoLoginCookie;
    if (autoLogin != null) {
      if (autoLogin.equals("on")) {
        autoLoginCookie = new Cookie("token", md5Util.StringToMD5(queryBean.getUsername()));
        autoLoginCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(autoLoginCookie);
      }
    }
    sessionCookie.setMaxAge(30 * 60);
    response.addCookie(sessionCookie);

    response.sendRedirect("/SpecialTopic/MemberFunction/MemberInfo.jsp");

  }
}


