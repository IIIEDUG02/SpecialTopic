package net.ddns.iiiedug02.demoWS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(myHandler(), "/websocket")// 入口路径为websocket
        .addInterceptors(new SpringWebSocketHandler()).setAllowedOrigins("*");
  }

  @Bean
  public WebSocketHandler myHandler() {
    return new MySocketHandler();
  }

  @Bean
  public HttpSessionHandshakeInterceptor myHandlerInterceptor() {
    return new SpringWebSocketHandler();
  }
}
