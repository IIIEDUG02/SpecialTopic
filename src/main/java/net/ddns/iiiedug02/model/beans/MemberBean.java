package net.ddns.iiiedug02.model.beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * JavaBean物件，對應資料庫中的members資料表
 */
@Entity
@Table(name = "members")
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
  private MemberDetailBean memberDetail;

  public int getUid() {
    return uid;
  }

  public MemberBean setUid(int uid) {
    this.uid = uid;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public MemberBean setUsername(String username) {
    // MD5Util md5u = new MD5Util();
    this.username = username;
    // this.token = md5u.StringToMD5(username);
    return this;
  }

  public String getPassword() {
    return password;
  }

  public MemberBean setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getRoles() {
    return roles;
  }

  public MemberBean setRoles(String roles) {
    this.roles = roles;
    return this;
  }

  public short getActivated() {
    return activated;
  }

  public MemberBean setActivated(short activated) {
    this.activated = activated;
    return this;
  }

  // public String getToken() {
  // return token;
  // }
  //
  //
  // public MemberBean setToken(String token) {
  // this.token = token;
  // return this;
  // }

  public MemberDetailBean getMemberDetail() {
    return memberDetail;
  }


  public MemberBean setMemberDetail(MemberDetailBean memberDetail) {
    this.memberDetail = memberDetail;
    return this;
  }


  @Override
  public String toString() {
    return "MemberBean [uid=" + uid + ", username=" + username + ", password=" + password
        + ", roles=" + roles + ", activated=" + activated + /* ", token=" + token + */ "]";
  }

}
