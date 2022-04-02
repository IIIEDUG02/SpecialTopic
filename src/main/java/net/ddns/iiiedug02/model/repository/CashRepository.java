package net.ddns.iiiedug02.model.repository;

import java.util.List;
import java.util.Map;
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
  public List<C2BBean> findByUid(int uid);

  /**
   * 輸入課程ID(cid)，回傳該課程的紀錄
   *
   * @param int cid
   * @return List<C2BBean>
   */
  public List<C2BBean> findByCid(int cid);

  public C2BBean findByUidAndCid(int uid, int cid);


  /**
   * 輸入指定年份，回傳List<[統計, cid]>
   */
  @Query(
      value = "select top 5 count(cid) as countcid, cid from c2b where year(order_date) = ?1 GROUP BY cid order by count(cid) DESC;",
      nativeQuery = true)
  public List<Map<String, Integer>> getYearTop5Class(int year);

  @Query(
	      value = "select top 5 count(cid) as countcid, cid from c2b where year(order_date) = ?1 and month(order_date) = ?2 GROUP BY cid order by count(cid) DESC;",
	      nativeQuery = true)
	  public List<Map<String, Integer>> getMonthTop5Class(int year, int month);
  
  @Query(
	      value = "SELECT c.cid , avg(DATEDIFF(year,md.birthday,GETDATE())) as avgAge \r\n"
	      		+ "from c2b c join member_details md on c.uid = md.uid GROUP by c.cid order by c.cid;",
	      nativeQuery = true)
	  public List<Map<String, Integer>> getAverageAge();

}
