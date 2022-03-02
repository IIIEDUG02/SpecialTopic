package net.ddns.iiiedug02.javaconfigs;

import java.util.ArrayList;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import net.ddns.iiiedug02.utils.MD5Util;
import net.ddns.iiiedug02.utils.SqlDateUtil;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "net.ddns.iiiedug02")
@PropertySource("classpath:/net/ddns/iiiedug02/javaconfigs/init.properties")
@EnableTransactionManagement
public class RootConfig {
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

  // @Bean
  // public DataSource dataSource() throws IllegalArgumentException, NamingException {
  // JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
  // jndiBean.setJndiName("java:comp/env/connectSqlServerJdbc/SystemService");
  // jndiBean.afterPropertiesSet();
  // return (DataSource) jndiBean.getObject();
  // }

  @Bean
  public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException {
    Properties hbnp = new Properties();
    // hbnp.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
    hbnp.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
    hbnp.setProperty("hibernate.show_sql", "true");
    hbnp.setProperty("hibernate.format_sql", "true");
    hbnp.setProperty("hibernate.current_session_context_class", "thread");
    LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
    sf.setDataSource(dataSource());
    sf.setPackagesToScan(hibernateScanPath);
    sf.setHibernateProperties(hbnp);
    return sf;
  }

  @Bean()
  @Autowired
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager txMgr = new HibernateTransactionManager();
    txMgr.setSessionFactory(sessionFactory);
    return txMgr;
  }

  @Bean
  public MD5Util md5Util() {
    return new MD5Util();
  }

  @Bean
  public SqlDateUtil sqlDateUtil() {
    return new SqlDateUtil();
  }


  @Bean
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver cmResolver = new CommonsMultipartResolver();
    cmResolver.setDefaultEncoding("UTF-8");
    return cmResolver;
  }

  @Bean
  public MappingJackson2JsonView jsonView() {
    MappingJackson2JsonView jv = new MappingJackson2JsonView();
    jv.setPrettyPrint(true);
    return jv;
  }

  @Bean
  public Jaxb2Marshaller jaxb2() {
    Jaxb2Marshaller jm = new Jaxb2Marshaller();
    jm.setPackagesToScan("net.ddns.iiiedug02");
    return jm;
  }

  @Bean
  @Autowired
  public ContentNegotiatingViewResolver cnvr() {
    ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
    java.util.List<View> viewList = new ArrayList<>();
    viewList.add(jsonView());
    cnvr.setDefaultViews(viewList);
    return cnvr;
  }

}
