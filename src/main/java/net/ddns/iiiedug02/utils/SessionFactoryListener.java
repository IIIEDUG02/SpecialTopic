package net.ddns.iiiedug02.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SessionFactoryListener implements ServletContextListener {

  public SessionFactoryListener() {}

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    HibernateUtil.getSessionFactory();
    System.out.println("SessionFactory Created!!");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    HibernateUtil.closeSessionFactory();
    System.out.println("SessionFactory Closed!!");
  }



}
