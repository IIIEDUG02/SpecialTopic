package net.ddns.iiiedug02.model.beans;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "MemberDetails")
public class MemberDetailsBean implements Serializable {

  private static final long serialVersionUID = 2L;

  @GenericGenerator(name = "generator", strategy = "foreign",
      parameters = @Parameter(name = "property", value = "members"))
  @Id
  @Column(name = "username")
  @GeneratedValue(generator = "SharedPrimaryKeyGenerator")
  private String username;


  @Column(name = "address")
  private String address;
  @Column(name = "phone")
  private String phone;
  @Column(name = "fullname")
  private String fullname;
  @Column(name = "email")
  private String email;
  @Column(name = "birthday")
  private Date birthday;
  @Column(name = "job")
  private String job;


  // @OneToOne(fetch = FetchType.LAZY)
  // @PrimaryKeyJoinColumn(name = "username", referencedColumnName = "username")
  // private MemberBean member;

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

  // public MemberBean getMember() {
  // return member;
  // }
  //
  // public void setMember(MemberBean member) {
  // this.username = member.getUsername();
  // this.member = member;
  // }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  @Override
  public String toString() {
    return "MemberDetailsBean [username=" + username + ", address=" + address + ", phone=" + phone
        + ", fullname=" + fullname + ", email=" + email + ", birthday=" + birthday + ", job=" + job
        + "]";
  }

}
