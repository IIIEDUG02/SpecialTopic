package net.ddns.iiiedug02.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import net.ddns.iiiedug02.model.services.MemberService;

/**
 * Servlet implementation class DeleteMember
 */
@WebServlet("/DeleteMember")
public class MemberDelete extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public MemberDelete() {
    super();
  }

  private MemberService memberService;

  @Override
  public void init() throws ServletException {
    ServletContext application = getServletContext();
    WebApplicationContext context =
        WebApplicationContextUtils.getWebApplicationContext(application);
    memberService = context.getBean("memberService", MemberService.class);
    super.init();
  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String mid = request.getParameter("mid");
    if (null == mid) {
      // early return
    }

    boolean result = memberService.delete(mid);

    if (result) {
      response.getWriter().println("Success");
    } else {
      response.getWriter().println("Failed");
    }


  }

}
