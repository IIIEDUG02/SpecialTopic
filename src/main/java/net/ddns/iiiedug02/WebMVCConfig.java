package net.ddns.iiiedug02;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login_page").setViewName("member/loginPage");
    registry.addViewController("/logout_page").setViewName("logoutPage");
    registry.addViewController("/registerPage1").setViewName("registerPage1");
    registry.addViewController("/registerPage2").setViewName("registerPage2");
    // 模板
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/about").setViewName("about");
    registry.addViewController("/courses").setViewName("courses");
    registry.addViewController("/trainers").setViewName("trainers");
    registry.addViewController("/events").setViewName("events");
    registry.addViewController("/pricing").setViewName("pricing");
    registry.addViewController("/contact").setViewName("contact");
  }

}
