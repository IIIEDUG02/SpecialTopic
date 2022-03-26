package net.ddns.iiiedug02.model.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.stereotype.Component;

@Entity
@Component
public class ShoppingCart {

  @Id
  private int uid;

  @ManyToOne
  private int cid;
  
  @
}
