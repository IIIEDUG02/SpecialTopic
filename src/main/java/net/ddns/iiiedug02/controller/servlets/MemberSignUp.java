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
import net.ddns.iiiedug02.model.beans.MemberDetailsBean;
import net.ddns.iiiedug02.model.services.MemberService;
import net.ddns.iiiedug02.utils.SqlDateUtil;

/**
 * 執行資料庫資料新增動作
 */
@WebServlet("/SignUp")
public class MemberSignUp extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public MemberSignUp() {
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

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String fullname = request.getParameter("fullname");
    String email = request.getParameter("email");
    String birthday = request.getParameter("birthday");
    String address = request.getParameter("address");
    String phone = request.getParameter("phone");
    String job = request.getParameter("job");

    // 新建MemberBean
    MemberBean signMember = new MemberBean();
    MemberDetailsBean signDetail = new MemberDetailsBean();

    signDetail.setAddress(address);
    signDetail.setPhone(phone);
    signDetail.setBirthday(SqlDateUtil.strToDate(birthday));
    signDetail.setFullname(fullname);
    signDetail.setJob(job);
    signDetail.setEmail(email);

    signMember.setUsername(username);
    signMember.setPassword(password);

    signDetail.setMember(signMember);
    signMember.setMemberDetail(signDetail);

    memberService.addMember(signMember);
    response.sendRedirect("Login.jsp");
  }

}
