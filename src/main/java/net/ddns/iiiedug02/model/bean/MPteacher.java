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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "MPTEACHER")
@Component
public class MPteacher implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "TEACHERID")
  private int teacherID;

  @Column(name = "MONTH")
  private int month;

  @Column(name = "MONTHAMOUNT")
  private int monthAmount;

  // @OneToOne(fetch = FetchType.LAZY, mappedBy = "ypteacher", cascade = CascadeType.ALL,
  // targetEntity = MemberInformation.class)
  @ManyToOne(fetch = FetchType.LAZY)
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

public int getMonth() {
	return month;
}

public void setMonth(int month) {
	this.month = month;
}

public int getMonthAmount() {
	return monthAmount;
}

public void setMonthAmount(int monthAmount) {
	this.monthAmount = monthAmount;
}

  

  public void setMemberInformation(MemberInformation memberInformation) {
    this.memberInformation = memberInformation;
  }

  public MemberInformation getMemberInformation() {
    return memberInformation;
  }

//  @Override
//  public String toString() {
//    return "YPteacher [id=" + id + ", teacherID=" + teacherID + ", year=" + year + ", yearAmount="
//        + yearAmount + ", memberInformation=" + memberInformation + "]";
//  }



}
