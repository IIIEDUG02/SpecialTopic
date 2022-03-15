package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "member_roles")
public class MemberRole implements GrantedAuthority, Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @GenericGenerator(name = "generator", strategy = "foreign",
      parameters = @Parameter(name = "property", value = "member"))
  @Column(name = "uid", insertable = false, updatable = false)
  @GeneratedValue(generator = "generator")
  private int uid;

  @Column(name = "role")
  private String role;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "uid", referencedColumnName = "uid")
  private Member member;

  @Override
  public String getAuthority() {
    return this.role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }


}
