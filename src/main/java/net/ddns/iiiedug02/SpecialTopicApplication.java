package net.ddns.iiiedug02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"net.ddns.iiiedug02"})
@EntityScan(basePackages = {"net.ddns.iiiedug02"})
@PropertySource(
    value = {"classpath:properties/jdbc.properties", "classpath:properties/hibernate.properties"},
    ignoreResourceNotFound = true)
public class SpecialTopicApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpecialTopicApplication.class, args);
  }
}
