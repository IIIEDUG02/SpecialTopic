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
 * 過濾至MemberList.jsp及MemberInfo.jsp的Request，檢查是否為登入狀態，若非登入狀態則導向Login.jsp，若為登入狀態則chain.doFilter
 */
@WebFilter(urlPatterns = {"/MemberFunction/*"})
public class MemberFunctionFilter extends HttpFilter implements Filter {

  private static final long serialVersionUID = 1L;

  public MemberFunctionFilter() {
    super();
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest hsreq = (HttpServletRequest) request;
    HttpServletResponse hsresp = (HttpServletResponse) response;
    HttpSession httpsession = hsreq.getSession(false);
    MemberBean mb;

    // 檢查是否有Session
    if (null == httpsession) {
      hsresp.sendRedirect("Login.jsp");
    }

    // 假如沒有登入狀態，跳轉Login.jsp
    mb = (MemberBean) httpsession.getAttribute("loginBean");
    if (null == mb) {
      hsresp.sendRedirect("Login.jsp");

      // 假如有登入狀態，繼續執行
    } else {
      chain.doFilter(request, response);
      return;
    }
  }
}
