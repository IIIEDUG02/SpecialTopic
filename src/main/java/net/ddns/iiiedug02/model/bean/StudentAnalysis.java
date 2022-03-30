package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@IdClass(ClassBean.class)
@Entity
@Table(name = "STUDENTANALYSIS")
@Component
public @Data class StudentAnalysis implements Serializable {

  private static final long serialVersionUID = 1L;

  
  

  @Id
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "classID", referencedColumnName = "CID")
  @JsonIgnore
  private ClassBean classbean;

  @Column(name = "AVERAGEAGE")
  private int averageAge;

  @Column(name = "JOB")
  private String job;

}
