package net.ddns.iiiedug02.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import net.ddns.iiiedug02.beans.MemberBean;
import net.ddns.iiiedug02.beans.MemberDetailsBean;
import net.ddns.iiiedug02.services.MemberService;

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

    signDetail.setUsername(username);
    signDetail.setAddress(address);
    signDetail.setPhone(phone);
    signDetail.setBirthday(strToDate(birthday));
    signDetail.setFullname(fullname);
    signDetail.setJob(job);
    signDetail.setEmail(email);

    signMember.setUsername(username);
    signMember.setPassword(password);

    signDetail.setMember(signMember);
    signMember.setMemberDetail(signDetail);

    memberService.addMember(signMember);
  }

  public java.sql.Date strToDate(String strDate) {
    String str = strDate;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date d = null;
    try {
      d = format.parse(str);
    } catch (Exception e) {
      e.printStackTrace();
    }
    java.sql.Date date = new java.sql.Date(d.getTime());
    return date;
  }

}
