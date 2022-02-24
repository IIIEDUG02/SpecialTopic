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
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.services.MemberService;

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
  private MemberBean adminBean;

  @Autowired
  private MemberService memberService;

  @Autowired
  private StandardPBEStringEncryptor strongEncryptor;

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

    // 資料庫物查詢
    MemberBean queryBean = memberService.selectByUsername(username);

    // 建立Session
    HttpSession httpsession = request.getSession(true);
    httpsession.setMaxInactiveInterval(180);// 單位為秒

    // 假如等於最高權限 adminBean
    if (username.contentEquals(adminBean.getUsername())
        && password.contentEquals(adminBean.getPassword())) {
      httpsession.setAttribute("loginBean", adminBean);
      Cookie sessionCookie = new Cookie(jsessionid, httpsession.getId());
      Cookie autoLoginCookie;
      if (autoLogin != null) {
        if (autoLogin.equals("on")) {
          autoLoginCookie =
              new Cookie("username", strongEncryptor.encrypt(adminBean.getUsername()));
          autoLoginCookie.setMaxAge(24 * 60 * 60);
          response.addCookie(autoLoginCookie);
        }
      }
      sessionCookie.setMaxAge(30 * 60);
      response.addCookie(sessionCookie);

      // 重新指向至Login.jsp，讓LoginFilter引導
      response.sendRedirect("/SpecialTopic/MemberFunction/MemberList.jsp");
    } else {
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
      Cookie sessionCookie = new Cookie(jsessionid, httpsession.getId());
      Cookie autoLoginCookie;
      if (autoLogin != null) {
        if (autoLogin.equals("on")) {
          autoLoginCookie =
              new Cookie("username", strongEncryptor.encrypt(queryBean.getUsername()));
          autoLoginCookie.setMaxAge(24 * 60 * 60);
          response.addCookie(autoLoginCookie);
        }
      }
      sessionCookie.setMaxAge(30 * 60);
      response.addCookie(sessionCookie);

      response.sendRedirect("/SpecialTopic/MemberFunction/MemberInfo.jsp");

    }
  }
}


