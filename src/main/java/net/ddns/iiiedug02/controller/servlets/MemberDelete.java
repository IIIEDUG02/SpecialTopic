package net.ddns.iiiedug02.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import net.ddns.iiiedug02.model.services.MemberService;

/**
 * 執行資料庫資料刪除動作
 */
@WebServlet("/MemberFunction/MemberDelete")
public class MemberDelete extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public MemberDelete() {
    super();
  }

  @Autowired
  private MemberService memberService;

  private WebApplicationContext springContext;

  @Override
  public void init(final ServletConfig config) throws ServletException {
    super.init(config);
    springContext =
        WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
    final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
    beanFactory.autowireBean(this);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String username = request.getParameter("username");
    if (null == username) {
      // early return
    }
    memberService.delete(username);
    response.sendRedirect("MemberList.jsp");
  }
}
