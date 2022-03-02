package net.ddns.iiiedug02.controller.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnlineCounter
 *
 */
@WebListener
public class OnlineUsers implements HttpSessionListener {

  public static int count = 0;

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    OnlineUsers.count++;
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    OnlineUsers.count--;
  }
}
