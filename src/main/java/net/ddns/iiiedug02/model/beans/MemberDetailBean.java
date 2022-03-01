package net.ddns.iiiedug02.model.beans;

import java.io.Serializable;
import java.sql.Date;
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

/**
 * JavaBean物件，對應資料庫中的memberDetail資料表
 */
@Entity
@Table(name = "memberDetails")
public class MemberDetailBean implements Serializable {

  private static final long serialVersionUID = 2L;

  @GenericGenerator(name = "generator", strategy = "foreign",
      parameters = @Parameter(name = "property", value = "member"))
  @Id
  @Column(name = "uid")
  @GeneratedValue(generator = "generator")
  private int uid;


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


  @OneToOne(fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private MemberBean member;


  public int getUid() {
    return uid;
  }

  public MemberDetailBean setUid(int uid) {
    this.uid = uid;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public MemberDetailBean setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public MemberDetailBean setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public String getFullname() {
    return fullname;
  }

  public MemberDetailBean setFullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public MemberDetailBean setEmail(String email) {
    this.email = email;
    return this;
  }

  public Date getBirthday() {
    return birthday;
  }

  public MemberDetailBean setBirthday(Date birthday) {
    this.birthday = birthday;
    return this;
  }

  public String getJob() {
    return job;
  }

  public MemberDetailBean setJob(String job) {
    this.job = job;
    return this;
  }

  public MemberBean getMember() {
    return member;
  }

  public MemberDetailBean setMember(MemberBean member) {
    this.member = member;
    return this;
  }

  @Override
  public String toString() {
    return "MemberDetailsBean [uid=" + uid + ", address=" + address + ", phone=" + phone
        + ", fullname=" + fullname + ", email=" + email + ", birthday=" + birthday + ", job=" + job
        + "]";
  }



}
