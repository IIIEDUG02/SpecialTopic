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
    http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/", "/js/**", "/css/**", "/img/**", "/assets/**", "/*")
        .permitAll().antMatchers(HttpMethod.POST, "/registerAction1", "/registerAction2")
        .permitAll().antMatchers("/cashflow/**").hasRole("admin");

    http.authorizeRequests().anyRequest().authenticated();

    http.formLogin().loginPage("/login_page").usernameParameter("username")
        .passwordParameter("password").defaultSuccessUrl("/").permitAll();
    http.rememberMe().tokenValiditySeconds(86400).key("rememberMe-key");
    http.logout().deleteCookies("JESSIONID", "rememberMe-key").logoutUrl("/logout_page");

    http.cors().and().csrf().disable();
  }

}
