package net.ddns.iiiedug02.model.bean;

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
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "class_details")
@Component
public class ClassDetailsBean {

  @GenericGenerator(name = "generator", strategy = "foreign",
      parameters = @Parameter(name = "property", value = "class"))
  @Id
  @Column
  @GeneratedValue(generator = "generator")
  private int cid;
  @Column(name = "descript")
  private String descript;
  @Column(name = "needed_tool")
  private String needed_tool;
  @Column(name = "stu_required")
  private String stu_required;
  @Column(name = "goal")
  private String goal;
  @Column(name = "video")
  private String video;
  @Column(name = "length_min")
  private int length_min;

  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private ClassBean classbean;


  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  public String getDescript() {
    return descript;
  }

  public void setDescript(String descript) {
    this.descript = descript;
  }

  public String getNeeded_tool() {
    return needed_tool;
  }

  public void setNeed_tool(String needed_tool) {
    this.needed_tool = needed_tool;
  }

  public String getStu_required() {
    return stu_required;
  }

  public void setStu_required(String stu_required) {
    this.stu_required = stu_required;
  }

  public String getGoal() {
    return goal;
  }

  public void setGoal(String goal) {
    this.goal = goal;
  }

  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

  public int getLength_min() {
    return length_min;
  }

  public void setLength_min(int length_min) {
    this.length_min = length_min;
  }

  public ClassBean getClassbean() {
    return classbean;
  }

  public void setClassbean(ClassBean classbean) {
    this.classbean = classbean;
  }

}
