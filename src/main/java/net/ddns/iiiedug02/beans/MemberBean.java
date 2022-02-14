package net.ddns.iiiedug02.beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class MemberBean implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "Username")
  private String username;
  @Column(name = "Password")
  private String password;


  @OneToOne(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
  private MemberDetailsBean memberDetail;


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

  public MemberDetailsBean getMemberDetail() {
    return memberDetail;
  }

  public void setMemberDetail(MemberDetailsBean memberDetail) {
    this.memberDetail = memberDetail;
  }

  @Override
  public String toString() {
    return "username=" + username + ", password=" + password
        + (memberDetail == null ? "" : ", " + memberDetail.toString());
  }


}
