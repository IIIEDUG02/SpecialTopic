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
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "student_analysis")
@Component
@Setter
@Getter
public class StudentAnalysis implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "ave_age")
  private int averageAge;

  @Column(name = "job")
  private String job;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cid", referencedColumnName = "cid")
  @JsonIgnore
  private ClassBean classBean;

}
