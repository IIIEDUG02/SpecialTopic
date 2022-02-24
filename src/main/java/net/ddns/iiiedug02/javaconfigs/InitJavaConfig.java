package net.ddns.iiiedug02.javaconfigs;

import java.util.Properties;
import javax.sql.DataSource;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.beans.MemberDetailsBean;

@Configuration
@PropertySource("classpath:/net/ddns/iiiedug02/javaconfigs/init.properties")
public class InitJavaConfig {
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

  // Jasypt
  @Value("${JASYPT_ALGORITHM}")
  private String jaspyptAlgorithm;
  @Value("${JASYPT_PASSWORD}")
  private String jasyptPassword;

  @Bean
  public MemberBean adminBean() {
    MemberBean mb = new MemberBean();
    mb.setActivated((short) 1);
    mb.setAuth("admin");
    mb.setPassword(adminPassword);
    mb.setUsername(adminUsername);

    MemberDetailsBean mbd = new MemberDetailsBean();
    mbd.setFullname("最高權限");

    mb.setMemberDetail(mbd);
    mbd.setMember(mb);
    return mb;
  }

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
  public StandardPBEStringEncryptor strongEncryptor() {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setAlgorithm(jaspyptAlgorithm);
    encryptor.setPassword(jasyptPassword);
    return encryptor;
  }
}
