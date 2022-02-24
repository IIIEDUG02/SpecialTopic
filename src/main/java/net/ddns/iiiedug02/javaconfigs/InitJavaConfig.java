package net.ddns.iiiedug02.javaconfigs;

import java.util.Properties;
import javax.sql.DataSource;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.beans.MemberDetailsBean;

@Configuration
public class InitJavaConfig {
  // DataSource
  private static final String DB_URL =
      "jdbc:sqlserver://iiiedug02.ddns.net:1555;DatabaseName=db;encrypt=true;trustServerCertificate=true";
  private static final String DB_USERNAME = "iiiedug02";
  private static final String DB_PASSWORD = "1113DU902db";
  private static final String DB_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

  // Default Admin
  private static final String ADMIN_USERNAME = "adminByJavaConfig";
  private static final String ADMIN_PASSWORD = "abcd1234";

  // Hibernate
  private static final String HIBERNATE_SCAN_PATH = "net.ddns.iiiedug02";

  // Jasypt
  private static final String JASYPT_ALGORITHM = "PBEWithMD5AndTripleDES";
  private static final String JASYPT_PASSWORD = "IdS15qBbuGH582M";

  @Bean
  public MemberBean adminBean() {
    MemberBean mb = new MemberBean();
    mb.setActivated((short) 1);
    mb.setAuth("admin");
    mb.setPassword(ADMIN_PASSWORD);
    mb.setUsername(ADMIN_USERNAME);

    MemberDetailsBean mbd = new MemberDetailsBean();
    mbd.setFullname("最高權限");

    mb.setMemberDetail(mbd);
    mbd.setMember(mb);
    return mb;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(DB_CLASS_NAME);
    ds.setUrl(DB_URL);
    ds.setUsername(DB_USERNAME);
    ds.setPassword(DB_PASSWORD);
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
    sf.setPackagesToScan(HIBERNATE_SCAN_PATH);
    sf.setHibernateProperties(hbnp);
    return sf;
  }

  @Bean
  public StandardPBEStringEncryptor strongEncryptor() {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setAlgorithm(JASYPT_ALGORITHM);
    encryptor.setPassword(JASYPT_PASSWORD);
    return encryptor;
  }
}
