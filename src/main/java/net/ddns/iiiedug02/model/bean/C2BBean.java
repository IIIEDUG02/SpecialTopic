package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
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

/**
 * c2b : 客戶交易紀錄
 */
@Entity
@Table(name = "c2b")
@Component
public class C2BBean implements Serializable {

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

  @Column(name = "completed")
  private short completed;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "c2b", cascade = CascadeType.ALL)
  @JoinColumn(name = "tid", referencedColumnName = "tid")
  private B2CBean b2c;

  public int getTid() {
    return tid;
  }

  public void setTid(int tid) {
    this.tid = tid;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public short getCompleted() {
    return completed;
  }

  public void setCompleted(short completed) {
    this.completed = completed;
  }

  public B2CBean getB2c() {
    return b2c;
  }

  public void setB2c(B2CBean b2c) {
    this.b2c = b2c;
  }

  @Override
  public String toString() {
    return "C2BBean [tid=" + tid + ", uid=" + uid + ", cid=" + cid + ", orderDate=" + orderDate
        + ", completed=" + completed + "]";
  }



}
