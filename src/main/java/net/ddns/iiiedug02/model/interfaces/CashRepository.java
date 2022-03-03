package net.ddns.iiiedug02.model.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.ddns.iiiedug02.model.bean.C2BBean;

public interface CashRepository extends JpaRepository<C2BBean, Integer> {

  @Query(value = "from C2BBean where uid = ?1")
  public List<C2BBean> findByUid(int uid);

  @Query(value = "from C2BBean where cid = ?1")
  public List<C2BBean> findByCid(int cid);
}
