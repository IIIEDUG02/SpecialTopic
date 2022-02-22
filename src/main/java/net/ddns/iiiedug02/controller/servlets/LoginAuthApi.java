package net.ddns.iiiedug02.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.google.gson.JsonObject;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.services.MemberService;
import net.ddns.iiiedug02.utils.RequestHeadersUtil;

/**
 * 執行登入驗證，並回饋是否帳號密碼錯誤，若驗證成功則藉由LoginFilter，執行頁面跳轉
 */
@WebServlet("/LoginAuthApi")
public class LoginAuthApi extends HttpServlet {
  private static final long serialVersionUID = 3L;

  public LoginAuthApi() {
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

    RequestHeadersUtil rhu = new RequestHeadersUtil(request);
    Map<String, String> map = rhu.getHeaders();
    String header = map.get("iiideug02");

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Access-Control-Allow-Origin", "*");
    JsonObject json = new JsonObject();

    if (header == null) {
      // Early Return Failed
      json.addProperty("result", "Bad Request");
    }
    if (header.equals("LoginAuth")) {

      // 取得Request的username跟password
      String username = request.getParameter("username");
      String password = request.getParameter("password");

      // 資料庫物查詢
      MemberBean queryBean = memberService.selectByUsername(username);

      // 假如帳號錯誤 memberBean = null
      if (queryBean == null) {
        json.addProperty("result", "User Not Found");
      } else if (!queryBean.getPassword().equals(password)) {
        json.addProperty("result", "Wrong Passwor");
      } else {
        // 建立Session
        HttpSession httpsession = request.getSession(true);
        httpsession.setMaxInactiveInterval(180);// 單位為秒

        // 設定Session及ookie
        httpsession.setAttribute("loginBean", queryBean);
        Cookie cookie = new Cookie("JSESSIONID", httpsession.getId());
        cookie.setMaxAge(30 * 60);
        response.addCookie(cookie);
        json.addProperty("result", "Success Loggin");
      }
      PrintWriter out = response.getWriter();
      out.print(json.toString());
      out.flush();
    }
  }
}


