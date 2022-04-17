package net.ddns.iiiedug02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import net.ddns.iiiedug02.model.service.MemberService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberService authmemberService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authmemberService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 頁面權限設定
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/js/**", "/css/**", "/img/**", "/assets/**",
                        "/*", "/**/api/**")
                .permitAll().antMatchers(HttpMethod.POST, "/registerAction1").permitAll();

        // 課程
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "class/showClassType/**", "/classphoto/**",
                        "/viewClass/**", "checkOwned/*")
                .permitAll().antMatchers(HttpMethod.GET, "/classvideo/**").authenticated();;

        // 登入設定
        http.formLogin().loginPage("/").loginProcessingUrl("/login").usernameParameter("username")
                .passwordParameter("password").defaultSuccessUrl("/").and().logout()
                .logoutUrl("/logout").invalidateHttpSession(true);

        // rememberMe設定
        http.rememberMe().tokenValiditySeconds(86400).key("rememberMe-key");

        // 登出設定
        http.logout().logoutUrl("/logout_page").deleteCookies("JESSIONID", "rememberMe-key")
                .logoutSuccessUrl("/");

        http.cors().and().csrf().disable();
    }
}
