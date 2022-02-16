package net.ddns.iiiedug02.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
    // 設定Response
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    // 設定Hibernate Session
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.getCurrentSession();

    // 取得Request的username跟password
    String USERNAME = request.getParameter("username");
    String PASSWORD = request.getParameter("password");

    // 載入加解密工具包
    JasyptUtil encryptorUtil = new JasyptUtil();

    // 資料庫物查詢
    Query<MemberBean> query =
        session.createQuery("from MemberBean where Username = :un", MemberBean.class);
    query.setParameter("un", USERNAME);
    MemberBean encryptedBean = query.uniqueResult();
    String DECRYPTPWD = encryptorUtil.decrypt(encryptedBean.getPassword());

    // 驗證密碼是否正確
    if (DECRYPTPWD.equals(PASSWORD)) {
      // 驗證成功後建立Session
      HttpSession httpsession = request.getSession(true);
      httpsession.setMaxInactiveInterval(60);// 單位為秒
      // 設定Session及ookie
      httpsession.setAttribute("username", USERNAME);
      Cookie cookie = new Cookie("JSESSIONID", httpsession.getId());
      cookie.setMaxAge(30 * 60);
      response.addCookie(cookie);
      // System.out.println(httpsession.getId());

      // 重新指向至HomePage
      response.sendRedirect("HomePage.jsp");
    } else {
      // 驗證失敗回傳Json {"result" : false}
      String ResultString = String.format("{ \"result\": %s }", DECRYPTPWD.equals(PASSWORD));
      out.print(ResultString);
      out.close();
    }
  }

}
