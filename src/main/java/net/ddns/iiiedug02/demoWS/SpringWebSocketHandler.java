package net.ddns.iiiedug02.demoWS;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import net.ddns.iiiedug02.model.bean.Member;

public class SpringWebSocketHandler extends HttpSessionHandshakeInterceptor {
  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
      WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
    System.out.println("Before Handshake");
    // 在握手之前将HttpSession中的用户，copy放到WebSocket Session中
    if (request instanceof ServletServerHttpRequest) {
      ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
      HttpSession session = servletServerHttpRequest.getServletRequest().getSession(true);
      if (null != session) {
        Member member = (Member) session.getAttribute("user");
        // WebSocket Session
        attributes.put("member", member);
      }
    }
    return super.beforeHandshake(request, response, wsHandler, attributes);
  }

  @Override
  public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
      WebSocketHandler wsHandler, Exception ex) {
    super.afterHandshake(request, response, wsHandler, ex);
  }

}
