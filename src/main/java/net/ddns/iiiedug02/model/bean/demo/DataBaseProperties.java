package net.ddns.iiiedug02.model.bean.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "database")
public class DataBaseProperties {
  private String driverneme;
  private String url;
  private String username;
  private String password;

  public String getDriverneme() {
    return driverneme;
  }

  public void setDriverneme(String driverneme) {
    this.driverneme = driverneme;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "DataBaseProperties [driverneme=" + driverneme + ", url=" + url + ", username="
        + username + ", password=" + password + "]";
  }



}
