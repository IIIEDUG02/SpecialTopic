package net.ddns.iiiedug02.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "curriculum")
@Component
public class CurriculumBean {
  @Id
  @Column(name = "CUID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int cuid;
  private String chapter;
  private String video_path;

  @Transient
  private int cid;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cid")
  private ClassBean classbean;

  public String getChapter() {
    return chapter;
  }

  public void setChapter(String chapter) {
    this.chapter = chapter;
  }

  public String getVideo_path() {
    return video_path;
  }

  public void setVideo_path(String video_path) {
    this.video_path = video_path;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  public ClassBean getClassbean() {
    return classbean;
  }

  public void setClassbean(ClassBean classbean) {
    this.classbean = classbean;
  }

  public int getCuid() {
    return cuid;
  }

  public void setCuid(int cuid) {
    this.cuid = cuid;
  }

}
