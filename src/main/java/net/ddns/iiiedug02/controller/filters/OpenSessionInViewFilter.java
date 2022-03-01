package net.ddns.iiiedug02.controller.filters;

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
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 過濾所有Request，實作1次瀏覽器的request及伺服器的response，只有1次Hibernate的commit(或rollback)
 */

@WebFilter("/*")
public class OpenSessionInViewFilter extends HttpFilter implements Filter {

  private static final long serialVersionUID = 1L;

  private SessionFactory sessionFactory;

  public OpenSessionInViewFilter() {
    super();
  }

  @Override
  public void init(FilterConfig fConfig) throws ServletException {
    ServletContext application = fConfig.getServletContext();
    WebApplicationContext context =
        WebApplicationContextUtils.getWebApplicationContext(application);
    sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
    ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers",
        "Origin, X-Requested-With, Content-Type, Accept, If-Modified-Since");
    ((HttpServletResponse) response).addHeader("Access-Control-Allow-Credentials", "true");

    Session session = sessionFactory.getCurrentSession();
    try {
      session.beginTransaction();
      System.out.println("---------- OSIVF:Begin Transaction ----------");
      chain.doFilter(request, response);
      session.getTransaction().commit();
      System.out.println("---------- OSIVF:Transaction Commit ----------");
    } catch (Exception e) {
      System.out.println("---------- OSIVF:Transaction Rollback ----------");
      session.getTransaction().rollback();
      // 報錯跳轉
      e.printStackTrace();
    }
  }

}
