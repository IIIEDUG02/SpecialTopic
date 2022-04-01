package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * c2b : 客戶交易紀錄
 */
@Entity
@Table(name = "c2b")
@Component
public @Data class C2BBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "tid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int tid;

  @Column(name = "uid")
  private int uid;

  @Column(name = "cid")
  private int cid;

  @Column(name = "order_date")
  private Date orderDate;

}
