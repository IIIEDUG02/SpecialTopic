package net.ddns.iiiedug02;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login_page").setViewName("member/loginPage");
        registry.addViewController("/logout_page").setViewName("logoutPage");
        registry.addViewController("/registerPage1").setViewName("member/registerPage1");
        registry.addViewController("/signout/success").setViewName("signout/success");
        registry.addViewController("/play").setViewName("class/curriculum-nilm");

        // 模板
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/courses").setViewName("courses");
        registry.addViewController("/trainers").setViewName("trainers");
        registry.addViewController("/events").setViewName("events");
        registry.addViewController("/pricing").setViewName("pricing");
        registry.addViewController("/contact").setViewName("contact");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        return cookieLocaleResolver;
    }
}
