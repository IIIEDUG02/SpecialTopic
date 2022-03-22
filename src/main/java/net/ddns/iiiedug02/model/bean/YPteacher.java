package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
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

@Entity
@Table(name = "YPTEACHER")
@Component
public class YPteacher implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "TEACHERID")
  private int teacherID;

  @Column(name = "YEAR")
  private int year;

  @Column(name = "YEARAMOUNT")
  private int yearAmount;

  // @OneToOne(fetch = FetchType.LAZY, mappedBy = "ypteacher", cascade = CascadeType.ALL,
  // targetEntity = MemberInformation.class)
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEACHERID", referencedColumnName = "uid", insertable = false,
      updatable = false)
  private MemberInformation memberInformation;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getTeacherID() {
    return teacherID;
  }

  public void setTeacherID(int teacherID) {
    this.teacherID = teacherID;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getYearAmount() {
    return yearAmount;
  }

  public void setYearAmount(int yearAmount) {
    this.yearAmount = yearAmount;
  }

  public void setMemberInformation(MemberInformation memberInformation) {
    this.memberInformation = memberInformation;
  }

  public MemberInformation getMemberInformation() {
    return memberInformation;
  }

  @Override
  public String toString() {
    return "YPteacher [id=" + id + ", teacherID=" + teacherID + ", year=" + year + ", yearAmount="
        + yearAmount + ", memberInformation=" + memberInformation + "]";
  }



}
