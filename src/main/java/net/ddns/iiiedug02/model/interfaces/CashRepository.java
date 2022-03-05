package net.ddns.iiiedug02.model.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.ddns.iiiedug02.model.bean.C2BBean;

public interface CashRepository extends JpaRepository<C2BBean, Integer> {


  /**
   * 輸入學生ID(uid)，回傳該學生的購課紀錄
   *
   * @param int uid
   * @return List<C2BBean>
   */
  @Query(value = "from C2BBean where uid = ?1")
  public List<C2BBean> findByUid(int uid);

  /**
   * 輸入課程ID(cid)，回傳該課程的紀錄
   *
   * @param int cid
   * @return List<C2BBean>
   */
  @Query(value = "from C2BBean where cid = ?1")
  public List<C2BBean> findByCid(int cid);
}
