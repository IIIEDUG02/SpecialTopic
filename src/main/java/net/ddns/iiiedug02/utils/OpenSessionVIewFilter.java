package net.ddns.iiiedug02.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


@WebFilter("/*")
public class OpenSessionVIewFilter implements Filter {

  public OpenSessionVIewFilter() {}



  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.getCurrentSession();
    try {
      session.beginTransaction();
      System.out.println("Started Transaction");


      response.setCharacterEncoding("UTF-8");
      request.setCharacterEncoding("UTF-8");
      chain.doFilter(request, response);

      session.getTransaction().commit();
      System.out.println("Commited Transaction");
    } catch (Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback();
      System.out.println("Rollbacked Transaction");
    } finally {
      System.out.println("Closed Session");
    }


  }


}
