package net.ddns.iiiedug02.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebFilter("/*")
public class OpenSessionInViewFilter extends HttpFilter implements Filter {

  private static final long serialVersionUID = 1L;

  private SessionFactory sessionFactory;

  public OpenSessionInViewFilter() {
    super();
  }

  public void init(FilterConfig fConfig) throws ServletException {
    ServletContext application = fConfig.getServletContext();
    WebApplicationContext context =
        WebApplicationContextUtils.getWebApplicationContext(application);
    sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");


    Session session = sessionFactory.getCurrentSession();
    try {
      session.beginTransaction();
      System.out.println("OSIVF:Begin Transaction");
      chain.doFilter(request, response);
      session.getTransaction().commit();
      System.out.println("OSIVF:Transaction Commit");
    } catch (Exception e) {
      session.getTransaction().rollback();
      e.printStackTrace();
      System.out.println("OSIVF:Transaction Rollback");
    }
  }

}
