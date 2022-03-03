package net.ddns.iiiedug02.model.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "c2b")
@Component
public class C2BBean implements Serializable {
  /**
   * c2b : 客戶交易紀錄
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "tid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int tid;

  @Id
  @Column(name = "sid")
  private int sid;

  @Id
  @Column(name = "cid")
  private int cid;

  // @Column(name = "orderDate")
  @Column(name = "oderDate")
  private Date orderDate;

  @Column(name = "completed")
  private short completed;

  public int getTid() {
    return tid;
  }

  public void setTid(int tid) {
    this.tid = tid;
  }

  public int getSid() {
    return sid;
  }

  public void setSid(int sid) {
    this.sid = sid;
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

  @Override
  public int hashCode() {
    return Objects.hash(cid, completed, orderDate, sid, tid);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    C2BBean other = (C2BBean) obj;
    return cid == other.cid && completed == other.completed
        && Objects.equals(orderDate, other.orderDate) && sid == other.sid && tid == other.tid;
  }

}
