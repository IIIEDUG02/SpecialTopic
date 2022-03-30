package net.ddns.iiiedug02.model.service;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.bean.C2BBean;
import net.ddns.iiiedug02.model.repository.CashRepository;


/**
 * Service物件，將完成指定商業邏輯的C2BBean物件，呼叫C2BDao執行增刪改查
 */
@Service
public class CashService {
  @Autowired
  private CashRepository cashRepository;



  @Transactional
  public C2BBean insert(C2BBean c2bBean) {
    return cashRepository.save(c2bBean);
  }

  @Transactional
  public C2BBean update(C2BBean c2bBean) {
    return cashRepository.save(c2bBean);
  }

  @Transactional(readOnly = true)
  public List<C2BBean> findAll() {
    return cashRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<C2BBean> findByUid(int uid) {
    return cashRepository.findByUid(uid);
  }

  @Transactional(readOnly = true)
  public List<C2BBean> findByCid(int cid) {
    return cashRepository.findByCid(cid);
  }

  /**
   * 輸入指定年份，回傳List<Map<統計, cid>> List<Map<String, Integer>> ol =
   * cashRepository.getYearTop5Class(year); for (Map<String, Integer> o : ol) {
   * System.out.println(o.get("cid")); }
   * 
   */
  @Transactional(readOnly = true)
  public List<Map<String, Integer>> getYearTop5Class(int year) {
    return cashRepository.getYearTop5Class(year);
  }

  @Transactional(readOnly = true)
  public List<Map<String, Integer>> getMonthTop5Class(int year, int month) {
    return cashRepository.getMonthTop5Class(year, month);
  }
  
  @Transactional(readOnly = true)
  public List<Map<String, Integer>> getAverageAge() {
    return cashRepository.getAverageAge();
  }

}
