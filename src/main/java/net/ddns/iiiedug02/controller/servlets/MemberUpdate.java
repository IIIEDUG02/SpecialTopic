package net.ddns.iiiedug02.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.services.MemberService;

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/MemberUpdate")
public class MemberUpdate extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public MemberUpdate() {
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
    String action = request.getParameter("action");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String activated = request.getParameter("activated");
    String auth = request.getParameter("auth");
    PrintWriter out = response.getWriter();
    if (action == null) {
      // Early Retrun
      response.sendRedirect("HomePage.jsp");
    } else if (action.equals("showinfo")) {
      MemberBean mb = memberService.selectByUsername(username);
      out.println("<html><meta charset='utf-8'><body>");
      out.println("<form method='get' action='/SpecialTopic/MemberUpdate'>");
      out.println(
          String.format("帳號<input type='text' name='username' value='%s'>", mb.getUsername()));
      out.println(
          String.format("密碼<input type='text' name='password' value='%s'>", mb.getPassword()));
      out.println(
          String.format("激活狀態<input type='text' name='activated' value='%s'>", mb.getActivated()));
      out.println(String.format("權限<input type='text' name='auth' value='%s'>", mb.getAuth()));
      out.println("<input type='hidden' name='action' name='' value='commit'>");
      out.println("<input type='submit' value='確認'>");
      out.println("</form>");
      out.println("</body></html>");
    } else if (action.equals("commit")) {

      short act = Short.valueOf(activated);
      MemberBean mb = new MemberBean();
      mb.setActivated(act);
      mb.setUsername(username);
      mb.setPassword(password);
      mb.setAuth(auth);
      memberService.updateMember(mb);
      response.sendRedirect("HomePage.jsp");
    }
  }

}
