package net.ddns.iiiedug02.demoWS;

import java.util.concurrent.CopyOnWriteArraySet;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MySocketHandler extends TextWebSocketHandler {
  private static CopyOnWriteArraySet<WebSocketSession> users =
      new CopyOnWriteArraySet<WebSocketSession>();
}
