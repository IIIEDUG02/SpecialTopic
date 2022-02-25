package net.ddns.iiiedug02.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.beans.MemberDetailBean;
import net.ddns.iiiedug02.model.services.MemberService;
import net.ddns.iiiedug02.utils.SqlDateUtil;

/**
 * 執行資料庫資料新增動作
 */
@WebServlet("/MemberAdd")
public class MemberAdd extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public MemberAdd() {
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

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession httpsession = request.getSession(true);

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
    MemberDetailBean signDetail = new MemberDetailBean();

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

    if (memberService.addMember(signMember)) {
      response.sendRedirect("Login.jsp");
    } else {
      httpsession.setAttribute("message", "帳號已註冊");
      httpsession.setAttribute("SignBean", signMember);
      response.sendRedirect("MemberFunction/MemberAdd.jsp");
    }

  }

}
