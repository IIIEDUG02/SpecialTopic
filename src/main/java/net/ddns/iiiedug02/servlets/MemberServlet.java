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
import net.ddns.iiiedug02.beans.MemberBean;
import net.ddns.iiiedug02.services.MemberService;
import net.ddns.iiiedug02.utils.HibernateUtil;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public MemberServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    Object att = request.getParameter("selectByUsername");

    if (att != null) {
      String username = att.toString();
      SessionFactory sf = HibernateUtil.getSessionFactory();
      Session ses = sf.getCurrentSession(); // EntityManagerFactory is closed

      try {
        ses.beginTransaction();
        MemberService ms = new MemberService(ses);
        MemberBean memberBean = ms.selectByUsername(username);
        out.println(memberBean.toString());
      } catch (Exception e) {
        ses.getTransaction().rollback();
        e.getStackTrace();
      } finally {
        // HibernateUtil.closeSessionFactory(); //結束位置要研究
      }
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
