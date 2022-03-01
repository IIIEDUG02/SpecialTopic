package net.ddns.iiiedug02.javaconfigs;

import java.util.Properties;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import net.ddns.iiiedug02.utils.MD5Util;
import net.ddns.iiiedug02.utils.SqlDateUtil;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:/net/ddns/iiiedug02/javaconfigs/init.properties")

public class WebAppBeans {
  // DataSource
  @Value("${DB_URL}")
  private String dbURL;
  @Value("${DB_USERNAME}")
  private String dbUsername;
  @Value("${DB_PASSWORD}")
  private String dbPassword;
  @Value("${DB_CLASS_NAME}")
  private String dbClassName;

  // Default Admin
  @Value("${ADMIN_USERNAME}")
  private String adminUsername;
  @Value("${ADMIN_PASSWOR}")
  private String adminPassword;

  // Hibernate
  @Value("${HIBERNATE_SCAN_PATH}")
  private String hibernateScanPath;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(dbClassName);
    ds.setUrl(dbURL);
    ds.setUsername(dbUsername);
    ds.setPassword(dbPassword);
    return ds;
  }

  @Bean
  public DataSource dataSource2() throws IllegalArgumentException, NamingException {
    JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
    jndiBean.setJndiName("java:comp/env/connectSqlServerJdbc/SystemService");
    jndiBean.afterPropertiesSet();
    return (DataSource) jndiBean;
  }



  // @Bean
  // public HibernateTransactionManager hibernateTransactionManager() {
  // HibernateTransactionManager htm = new HibernateTransactionManager();
  // htm.setDataSource(dataSource());
  // htm.setHibernateManagedSession(true);
  // htm.setSessionFactory((SessionFactory) sessionFactory());
  // return htm;
  // }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    Properties hbnp = new Properties();
    hbnp.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
    hbnp.setProperty("hibernate.show_sql", "true");
    hbnp.setProperty("hibernate.format_sql", "true");
    hbnp.setProperty("hibernate.current_session_context_class", "thread");
    LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
    sf.setDataSource(this.dataSource());
    sf.setPackagesToScan(hibernateScanPath);
    sf.setHibernateProperties(hbnp);
    return sf;
  }

  @Bean
  public MD5Util md5Util() {
    MD5Util md5u = new MD5Util();
    return md5u;
  }

  @Bean
  public SqlDateUtil sqlDateUtil() {
    SqlDateUtil sdu = new SqlDateUtil();
    return sdu;
  }
}
