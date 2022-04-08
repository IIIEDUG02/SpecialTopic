package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "class")
@Component
public class ClassBean implements Serializable {

	  private static final long serialVersionUID = 2L;

  @Id
  @Column(name = "CID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int cid;
  @Column(name = "price")
  private int price;
  @Column(name = "uid")
  private int uid;
  @Column(name = "title")
  private String title;
  @Column(name = "class_type")
  private String classType;
  @Column(name = "photo")
  private String photo;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "classbean", cascade = CascadeType.ALL)
  private ClassDetailsBean classDetailsBean;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "classbean", cascade = CascadeType.ALL)
  private Set<CurriculumBean> curriculumbean = new HashSet<CurriculumBean>(0);


  public Set<CurriculumBean> getCurriculumbean() {
    return curriculumbean;
  }

  public void setCurriculumbean(Set<CurriculumBean> curriculumbean) {
    this.curriculumbean = curriculumbean;
  }

  public ClassDetailsBean getClassDetailsBean() {
    return classDetailsBean;
  }

  public void setClassDetailsBean(ClassDetailsBean classDetailsBean) {
    this.classDetailsBean = classDetailsBean;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getClassType() {
    return classType;
  }

  public void setClassType(String classType) {
    this.classType = classType;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

}
