package net.ddns.iiiedug02.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import net.ddns.iiiedug02.util.SqlDateUtil;

@Configuration
@ComponentScan(basePackages = "net.ddns.iiiedug02")
public class InitConfig {
  @Bean
  public SqlDateUtil sqlDateUtil() {
    return new SqlDateUtil();
  }
}
