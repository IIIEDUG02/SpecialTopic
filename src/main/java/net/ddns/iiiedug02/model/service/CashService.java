package net.ddns.iiiedug02.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.bean.C2BBean;
import net.ddns.iiiedug02.model.interfaces.CashRepository;


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
  

}
