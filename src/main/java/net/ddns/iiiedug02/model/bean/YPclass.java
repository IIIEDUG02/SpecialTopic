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
@Table(name = "YPCLASS")
@Component
public class YPclass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CLASSID")
    private int classID;

    @Column(name = "YEAR")
    private int year;

    @Column(name = "YEARAMOUNT")
    private int yearAmount;

    // @Column(name = "priority")
    // private String priority;

    // @OneToOne(fetch = FetchType.LAZY, mappedBy = "ypteacher", cascade = CascadeType.ALL,
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
