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
import org.springframework.stereotype.Component;

@Entity
@Table(name = "MPCLASS")
@Component
public class MPclass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CLASSID")
    private int classID;

    @Column(name = "MONTH")
    private int month;

    @Column(name = "MONTHAMOUNT")
    private int monthAmount;

    // @OneToOne(fetch = FetchType.LAZY, mappedBy = "ypteacher", cascade =
    // CascadeType.ALL,
    // targetEntity = MemberInformation.class)
    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "TEACHERID", referencedColumnName = "uid", insertable = false,
    // updatable = false)
    // private MemberInformation memberInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASSID", referencedColumnName = "CID", insertable = false,
            updatable = false)
    private ClassBean classBean;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
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

    public ClassBean getClassBean() {
        return classBean;
    }

    public void setClassBean(ClassBean classBean) {
        this.classBean = classBean;
    }

    // public void setMemberInformation(MemberInformation memberInformation) {
    // this.memberInformation = memberInformation;
    // }
    //
    // public MemberInformation getMemberInformation() {
    // return memberInformation;
    // }

    // @Override
    // public String toString() {
    // return "YPteacher [id=" + id + ", teacherID=" + teacherID + ", year=" + year + ",
    // yearAmount="
    // + yearAmount + ", memberInformation=" + memberInformation + "]";
    // }

}
