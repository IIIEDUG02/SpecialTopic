package net.ddns.iiiedug02.javaconfigs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// mvc-servlet.xml
@Configuration
// <mvc:annotation-driven />
@EnableWebMvc
// <context:component-scan base-package="net.ddns.iiiedug02" />
@ComponentScan(basePackages = "net.ddns.iiiedug02")
public class WebAppServlets implements WebMvcConfigurer {

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.jsp("WEB-INF/views/", ".jsp");
    registry.order(6);
    WebMvcConfigurer.super.configureViewResolvers(registry);
  }

}
