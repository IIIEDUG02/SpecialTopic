package net.ddns.iiiedug02.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class MainFilter
 */
@WebFilter(urlPatterns = {"/Login.jsp"})
public class LoginJSPFilter extends HttpFilter implements Filter {

  private static final long serialVersionUID = 1L;

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest hsreq = (HttpServletRequest) request;
    HttpServletResponse hsresp = (HttpServletResponse) response;
    HttpSession httpsession = hsreq.getSession(false);

    if (null != httpsession) {
      String username = (String) httpsession.getAttribute("username");
      if (null != username) {
        hsresp.sendRedirect("HomePage.jsp");
        System.out.println(username);
      }
    }
    chain.doFilter(request, response);
  }
}
