package net.ddns.iiiedug02.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Picture")
@Component
public class Picture {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "filename")
  private String filename;
  @Column(name = "pic")
  private byte[] pic;

  public Picture() {}

  public Picture(String filename, byte[] pic) {
    this.filename = filename;
    this.pic = pic;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public byte[] getPicture() {
    return pic;
  }

  public void setPicture(byte[] pic) {
    this.pic = pic;
  };

}
