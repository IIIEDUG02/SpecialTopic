package net.ddns.iiiedug02.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "memberdetails")
public class MemberDetailsBean implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @GenericGenerator(name = "generator", strategy = "foreign",
      parameters = @Parameter(name = "property", value = "members"))
  @Id
  @Column(name = "Username")
  @GeneratedValue(generator = "generator")
  private String username;
  @Column(name = "Address")
  private String address;
  @Column(name = "Phone")
  private String phone;

  @OneToOne(fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private MemberBean member;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public MemberBean getMember() {
    return member;
  }

  public void setMember(MemberBean member) {
    this.member = member;
  }

  @Override
  public String toString() {
    return "address=" + address + ", phone=" + phone;
  }


}
