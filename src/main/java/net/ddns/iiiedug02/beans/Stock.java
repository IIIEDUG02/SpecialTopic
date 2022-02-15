package net.ddns.iiiedug02.beans;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Stock")
public class Stock {
  @Id
  @Column(name = "sotckid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int sotckid;

  @Column(name = "stockname")
  private String sotckname;

  @Column(name = "stockcode")
  private String sotckcode;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock", cascade = CascadeType.ALL)
  private Set<StockTransaction> stocktrans = new HashSet<StockTransaction>(0);

  public Stock() {}

  public Stock(String sotckname, String sotckcode) {
    this.sotckname = sotckname;
    this.sotckcode = sotckcode;
  }



  public Stock(String sotckname, String sotckcode, Set<StockTransaction> stocktrans) {
    this.sotckname = sotckname;
    this.sotckcode = sotckcode;
    this.stocktrans = stocktrans;
  }


  public int getSotckid() {
    return sotckid;
  }

  public void setSotckid(int sotckid) {
    this.sotckid = sotckid;
  }

  public String getSotckname() {
    return sotckname;
  }

  public void setSotckname(String sotckname) {
    this.sotckname = sotckname;
  }

  public String getSotckcode() {
    return sotckcode;
  }

  public void setSotckcode(String sotckcode) {
    this.sotckcode = sotckcode;
  }

  public Set<StockTransaction> getStocktrans() {
    return stocktrans;
  }

  public void setStocktrans(Set<StockTransaction> stocktrans) {
    this.stocktrans = stocktrans;
  }

}
