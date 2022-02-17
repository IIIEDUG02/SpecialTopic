package net.ddns.iiiedug02.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import net.ddns.iiiedug02.beans.MemberBean;
import net.ddns.iiiedug02.utils.HibernateUtil;
import net.ddns.iiiedug02.utils.JasyptUtil;

/**
 * Servlet implementation class LoginAuth
 */
@WebServlet("/LoginAuth")
public class LoginAuth extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public LoginAuth() {
    super();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String jsessionid = "JSESSIONID";

    // 設定Response
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    JasyptUtil encryptUtil = new JasyptUtil();

    // 設定Hibernate Session
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session hbsession = sf.getCurrentSession();

    // 取得Request的username跟password
    String USERNAME = request.getParameter("username");
    String PASSWORD = request.getParameter("password");

    // 資料庫物查詢
    Query<MemberBean> query =
        hbsession.createQuery("from MemberBean where username = :un", MemberBean.class);
    query.setParameter("un", USERNAME);
    MemberBean encryptBean = query.uniqueResult();

    MemberBean memberBean = new MemberBean().setUsername(encryptBean.getUsername())
        .setPassword(encryptUtil.decrypt(encryptBean.getPassword()));

    // 建立Session
    HttpSession httpsession = request.getSession(true);
    httpsession.setMaxInactiveInterval(180);// 單位為秒

    // 假如帳號錯誤 memberBean = null
    if (memberBean == null) {
      // 設定Session及ookie
      httpsession.setAttribute("message", "找不到帳號");
      Cookie cookie = new Cookie(jsessionid, httpsession.getId());
      cookie.setMaxAge(30 * 60);
      response.addCookie(cookie);

      // 驗證失敗倒回Login.jsp
      response.sendRedirect("Login.jsp");
      return;
    }

    if (!memberBean.getPassword().equals(PASSWORD)) {
      System.out.println("-----------------------");
      System.out.println(memberBean.getPassword());
      System.out.println("-----------------------");

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
    httpsession.setAttribute("username", USERNAME);
    Cookie cookie = new Cookie(jsessionid, httpsession.getId());
    cookie.setMaxAge(30 * 60);
    response.addCookie(cookie);

    // 重新指向至HomePage
    response.sendRedirect("HomePage.jsp");

  }
}


