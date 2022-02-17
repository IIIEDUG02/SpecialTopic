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
@Table(name = "Members")
public class MemberBean implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;


  @OneToOne(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
  private MemberDetailsBean memberDetail;


  public String getUsername() {
    return username;
  }

  public MemberBean setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public MemberBean setPassword(String password) {
    this.password = password;
    return this;
  }

  public MemberDetailsBean getMemberDetail() {
    return memberDetail;
  }

  public MemberBean setMemberDetail(MemberDetailsBean memberDetail) {
    this.memberDetail = memberDetail;
    return this;
  }

  @Override
  public String toString() {
    return "username=" + username + ", password=" + password
        + (memberDetail == null ? "" : ", " + memberDetail.toString());
  }

}
