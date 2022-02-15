package net.ddns.iiiedug02.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

  // @Override
  // protected void doGet(HttpServletRequest req, HttpServletResponse resp)
  // throws ServletException, IOException {
  // resp.setContentType("application/json");
  // resp.setCharacterEncoding("UTF-8");
  // PrintWriter out = resp.getWriter();
  //
  // SessionFactory sf = HibernateUtil.getSessionFactory();
  // Session session = sf.getCurrentSession();
  // MemberService mbs = new MemberService(session);
  //
  // // 取得Request的username跟password
  // String USERNAME = req.getParameter("username");
  // String PASSWORD = req.getParameter("password");
  //
  // // 載入加解密工具包
  // JasyptUtil encryptorUtil = new JasyptUtil();
  //
  // Query<MemberBean> query =
  // session.createQuery("from MemberBean where Username = :un", MemberBean.class);
  // query.setParameter("un", USERNAME);
  // MemberBean encryptedBean = query.uniqueResult();
  // String DECRYPTPWD = encryptorUtil.decrypt(encryptedBean.getPassword());
  //
  // String ResultString = String.format("{ \"result\": %s }", DECRYPTPWD.equals(PASSWORD));
  // out.print(ResultString);
  // out.close();
  // }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.getCurrentSession();
    // 取得Request的username跟password
    String USERNAME = request.getParameter("username");
    String PASSWORD = request.getParameter("password");

    // 載入加解密工具包
    JasyptUtil encryptorUtil = new JasyptUtil();

    Query<MemberBean> query =
        session.createQuery("from MemberBean where Username = :un", MemberBean.class);
    query.setParameter("un", USERNAME);
    MemberBean encryptedBean = query.uniqueResult();
    String DECRYPTPWD = encryptorUtil.decrypt(encryptedBean.getPassword());

    if (DECRYPTPWD.equals(PASSWORD)) {

    }



    String ResultString = String.format("{ \"result\": %s }", DECRYPTPWD.equals(PASSWORD));
    out.print(ResultString);
    out.close();
  }

}
