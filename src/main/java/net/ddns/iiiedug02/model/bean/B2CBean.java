package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import java.sql.Date;
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

/**
 * b2c : 公司支付紀錄
 */
@Entity
@Table(name = "b2c")
public class B2CBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @GenericGenerator(name = "generator", strategy = "foreign",
      parameters = @Parameter(name = "property", value = "c2b"))

  @Id
  @Column(name = "tid")
  @GeneratedValue(generator = "generator")
  private int tid;

  @Column(name = "order_date")
  private Date orderDate;

  @Column(name = "completed")
  private short completed;

  @OneToOne(fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private C2BBean c2b;

  public int getTid() {
    return tid;
  }

  public void setTid(int tid) {
    this.tid = tid;
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

  public C2BBean getC2b() {
    return c2b;
  }

  public void setC2b(C2BBean c2b) {
    this.c2b = c2b;
  }

}
