package net.ddns.iiiedug02.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StaticFilePathTest
 */
@WebServlet("/StaticFilePathTest")
public class StaticFilePathTest extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public StaticFilePathTest() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter w = response.getWriter();
    w.println("<html>");
    w.println("<body>");
    w.println("<a href='js/test.js'>測試靜態檔路徑</a>");
    w.println("</body>");
    w.println("</html>");
  }
}
