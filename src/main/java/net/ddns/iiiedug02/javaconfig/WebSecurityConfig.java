package net.ddns.iiiedug02.javaconfig;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  // @Override
  // public UserDetailsService userDetailsServiceBean() throws Exception {
  // // TODO Auto-generated method stub
  // return super.userDetailsServiceBean();
  // }
  //
  // @Override
  // protected UserDetailsService userDetailsService() {
  // // TODO Auto-generated method stub
  // return super.userDetailsService();
  // }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/cashflow/**").authenticated().anyRequest().permitAll()
        .and().formLogin().loginPage("/login").defaultSuccessUrl("/").and().rememberMe()
        .tokenValiditySeconds(86400).key("rememberMe-key");

  }

  @Override
  public void configure(WebSecurity web) throws Exception {

  }

}
