package net.ddns.iiiedug02.controller.filters;

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
import net.ddns.iiiedug02.model.beans.MemberBean;

/**
 * 過濾至Login.jsp的Request，檢查是否為登入狀態，並依據登入者權限跳轉指定功能頁面，若未則chain.doFilter
 */
@WebFilter(urlPatterns = {"/Login.jsp", "/Login.html"})
public class LoginFilter extends HttpFilter implements Filter {

  private static final long serialVersionUID = 1L;

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest hsreq = (HttpServletRequest) request;
    HttpServletResponse hsresp = (HttpServletResponse) response;
    HttpSession httpsession = hsreq.getSession(false);

    // 檢查是否有Session
    if (httpsession == null) {
      chain.doFilter(request, response);
      return;
    }

    // 檢查Session 是否有loginBean
    MemberBean loginBean = (MemberBean) httpsession.getAttribute("loginBean");
    if (null == loginBean) {
      chain.doFilter(request, response);
      return;
    }

    // 假如loginBean的權限是admin
    String currentAuth = loginBean.getAuth();
    if (currentAuth.equals("admin")) {
      hsresp.sendRedirect("MemberFunction/MemberList.jsp");
    } else {
      // 假如loginBean的權不是限是admin
      hsresp.sendRedirect("MemberFunction/MemberInfo.jsp");
    }

  }
}
