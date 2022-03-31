package net.ddns.iiiedug02.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.bean.ClassManagementBean;
import net.ddns.iiiedug02.model.repository.ClassManagementRepository;

@Service
@Transactional
public class ClassManagementService {

  @Autowired
  private ClassManagementRepository cmRepo;

  public ClassManagementBean insert(ClassManagementBean cmb) {
    return cmRepo.save(cmb);
  }

  public ClassManagementBean update(ClassManagementBean cmb) {
    return cmRepo.save(cmb);
  }

  @Transactional
  public List<ClassManagementBean> insertByList(List<ClassManagementBean> cmbBeanList) {
    return cmRepo.saveAll(cmbBeanList);
  }

  @Transactional(readOnly = true)
  public List<ClassManagementBean> findAll() {
    return cmRepo.findAll();
  }

  @Transactional(readOnly = true)
  public List<ClassManagementBean> findByUid(int uid) {
    return cmRepo.findByUid(uid);
  }

  @Transactional(readOnly = true)
  public List<ClassManagementBean> findByCid(int cid) {
    return cmRepo.findByCid(cid);
  }

  @Transactional(readOnly = true)
  public ClassManagementBean findByUidAndCid(int uid, int cid) {
    return cmRepo.findByUidAndCid(uid, cid);
  }
}
