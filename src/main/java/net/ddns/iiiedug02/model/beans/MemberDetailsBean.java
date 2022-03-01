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
 * JavaBean物件，對應資料庫中的memberDetails資料表
 */
@Entity
@Table(name = "memberDetails")
public class MemberDetailsBean implements Serializable {

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


  public void setUid(int uid) {
    this.uid = uid;
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


  public MemberBean getMember() {
    return member;
  }


  public void setMember(MemberBean member) {
    this.member = member;
  }


  @Override
  public String toString() {
    return "MemberDetailsBean [uid=" + uid + ", address=" + address + ", phone=" + phone
        + ", fullname=" + fullname + ", email=" + email + ", birthday=" + birthday + ", job=" + job
        + "]";
  }



}
