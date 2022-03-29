package net.ddns.iiiedug02.model.bean;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table(name = "ecpay_record")
@Setter
@Getter
public class EcpayRecord {

  @Id
  @Column(name = "order_id")
  @NonNull
  private String orderId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "uid", referencedColumnName = "uid")
  @NonNull
  private Member member;

  @Column(name = "cids")
  @NonNull
  private String cids;

  @Column(name = "trade_amount")
  @NonNull
  private int tradeAmount;

  @Column(name = "rtn_msg")
  private String rtnMsg;

  @Column(name = "order_date")
  private Date orderDate;

  @Column(name = "payment_type")
  private String paymentType;
}
