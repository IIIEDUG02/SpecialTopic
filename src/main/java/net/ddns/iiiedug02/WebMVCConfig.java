package net.ddns.iiiedug02;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login_page").setViewName("loginPage");
    registry.addViewController("/logout_page").setViewName("logoutPage");
    registry.addViewController("/").setViewName("welcome");
    registry.addViewController("/registerPage1").setViewName("registerPage1");
    registry.addViewController("/registerPage2").setViewName("registerPage2");
  }

}
