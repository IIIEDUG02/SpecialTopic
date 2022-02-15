package net.ddns.iiiedug02.beans;

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

@Entity
@Table(name = "StockTransaction")
public class StockTransaction {
  @Id
  @Column(name = "stocktransid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int stocktransid;

  @Column(name = "tradevolume")
  private int tradevolume;

  @Transient
  private int stockid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stockid")
  private Stock stock;

  public StockTransaction() {}

  public StockTransaction(int tradevolume) {
    this.tradevolume = tradevolume;
  }


  public int getStocktransid() {
    return stocktransid;
  }


  public void setStocktransid(int stocktransid) {
    this.stocktransid = stocktransid;
  }


  public int getTradevolume() {
    return tradevolume;
  }


  public void setTradevolume(int tradevolume) {
    this.tradevolume = tradevolume;
  }


  public int getStockid() {
    return stockid;
  }


  public void setStockid(int stockid) {
    this.stockid = stockid;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

}
