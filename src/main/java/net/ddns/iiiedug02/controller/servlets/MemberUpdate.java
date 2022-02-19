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
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.services.MemberService;
import net.ddns.iiiedug02.utils.SqlDateUtil;

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

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String activated = request.getParameter("activated");
    String auth = request.getParameter("auth");
    String fullname = request.getParameter("fullname");
    String address = request.getParameter("address");
    String phone = request.getParameter("phone");
    String job = request.getParameter("job");
    String birthday = request.getParameter("birthday");
    String email = request.getParameter("email");

    short act = Short.valueOf(activated);
    MemberBean mb = memberService.selectByUsername(username);

    mb.setActivated(act);
    mb.setUsername(username);
    mb.setPassword(password);
    mb.setAuth(auth);
    mb.getMemberDetail().setFullname(fullname);
    mb.getMemberDetail().setAddress(address);
    mb.getMemberDetail().setPhone(phone);
    mb.getMemberDetail().setJob(job);
    mb.getMemberDetail().setBirthday(SqlDateUtil.strToDate(birthday));
    mb.getMemberDetail().setEmail(email);

    memberService.updateMember(mb);
    response.sendRedirect("Login.jsp");
  }
}
