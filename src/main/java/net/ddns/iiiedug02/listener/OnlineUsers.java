package net.ddns.iiiedug02.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 統計session總數
 * 
 * @author Nilm
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
