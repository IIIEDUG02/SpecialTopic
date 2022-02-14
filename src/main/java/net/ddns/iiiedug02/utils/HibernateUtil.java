package net.ddns.iiiedug02.utils;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
  private static final SessionFactory factory = createSessionFactory();

  private static SessionFactory createSessionFactory() {
    StandardServiceRegistry rs = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory sf = new MetadataSources(rs).buildMetadata().buildSessionFactory();
    return sf;
  }

  public static SessionFactory getSessionFactory() {
    return factory;
  }

  public static void closeSessionFactory() {
    if (null != factory) {
      factory.close();
    }
  }

}
