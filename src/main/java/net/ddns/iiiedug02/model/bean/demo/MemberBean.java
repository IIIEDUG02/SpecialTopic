package net.ddns.iiiedug02.model.bean.demo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

/**
 * JavaBean物件，對應資料庫中的members資料表
 */
@Entity
@Table(name = "members")
@Component
public class MemberBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "uid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int uid;

  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "roles")
  private String roles = "normal";
  @Column(name = "activated")
  private short activated = 0;
  // @Column(name = "token")
  // private String token;
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
  @JoinColumn(name = "detailId", referencedColumnName = "id")
  private MemberDetailBean memberDetail;

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
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

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public short getActivated() {
    return activated;
  }

  public void setActivated(short activated) {
    this.activated = activated;
  }

  public MemberDetailBean getMemberDetail() {
    return memberDetail;
  }

  public void setMemberDetail(MemberDetailBean memberDetail) {
    this.memberDetail = memberDetail;
  }

  @Override
  public String toString() {
    return "MemberBean [uid=" + uid + ", username=" + username + ", password=" + password
        + ", roles=" + roles + ", activated=" + activated + ", memberDetail=" + memberDetail + "]";
  }

}
