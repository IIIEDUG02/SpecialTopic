package net.ddns.iiiedug02.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

  public List<ShoppingCart> findByUid(int uid);

  // 失敗
  public void deleteByUidAndClassBean(int uid, ClassBean cb);

  public ShoppingCart findByUidAndClassBean(int uid, ClassBean cb);

}
