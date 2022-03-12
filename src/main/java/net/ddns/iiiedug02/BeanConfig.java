package net.ddns.iiiedug02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import net.ddns.iiiedug02.util.SqlDateUtil;

@Configuration
@ComponentScan(basePackages = "net.ddns.iiiedug02")
public class BeanConfig {
  @Bean
  public SqlDateUtil sqlDateUtil() {
    return new SqlDateUtil();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
