package net.ddns.iiiedug02.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import net.ddns.iiiedug02.beans.MemberBean;
import net.ddns.iiiedug02.services.MemberService;
import net.ddns.iiiedug02.utils.HibernateUtil;
import net.ddns.iiiedug02.utils.JasyptUtil;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public SignUp() {
    super();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String fullname = request.getParameter("fullname");
    String birthday = request.getParameter("birthday");
    String address = request.getParameter("address");
    String phone = request.getParameter("phone");
    String job = request.getParameter("job");

    // 設定Hibernate Session
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session hbsession = sf.getCurrentSession();

    // 載入 MemberService
    MemberService ms = new MemberService(hbsession);

    // 載入加解密工具包
    JasyptUtil encryptorUtil = new JasyptUtil();

    // 新建MemberBean
    MemberBean signMember = new MemberBean();
    signMember.setUsername(username);
    signMember.setPassword(encryptorUtil.encrypt(password));
    if (ms.addMember(signMember)) {
      System.out.println("成功");
    } else {
      System.out.println("失敗");
    }
  }

}
