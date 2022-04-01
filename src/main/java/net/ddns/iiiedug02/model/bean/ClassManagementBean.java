package net.ddns.iiiedug02.model.bean;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "classmanagement")
@Component
@Setter
@Getter
public class ClassManagementBean {

  @Id
  @Column(name = "CMID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int cmid;

  private int status;

  private int cid;

  private String tid;

  private int uid;

  @Column(name = "order_date")
  private Date orderDate;

}
