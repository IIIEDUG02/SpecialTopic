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
  @Column(name = "auth")
  private String auth;
  @Column(name = "activate")
  private short activated = 1; // 0:true, 1:false


  @OneToOne(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
  private MemberDetailsBean memberDetail;


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


  public String getAuth() {
    return auth;
  }


  public void setAuth(String auth) {
    this.auth = auth;
  }


  public short getActivated() {
    return activated;
  }


  public void setActivated(short activated) {
    this.activated = activated;
  }


  public MemberDetailsBean getMemberDetail() {
    return memberDetail;
  }


  public void setMemberDetail(MemberDetailsBean memberDetail) {
    this.memberDetail = memberDetail;
  }


  @Override
  public String toString() {
    return "MemberBean [uid=" + uid + ", username=" + username + ", password=" + password
        + ", auth=" + auth + ", activated=" + activated + "]";
  }



}
