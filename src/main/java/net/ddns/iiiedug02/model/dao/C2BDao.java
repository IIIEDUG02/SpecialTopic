package net.ddns.iiiedug02.model.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.bean.C2BBean;

@Repository("c2bdao")
@Transactional
public class C2BDao {

  @Autowired
  private SessionFactory sessionFactory;

  public List<C2BBean> selectBySid(int sid) {
    Session session = sessionFactory.openSession();

    @SuppressWarnings("unchecked")
    Query<C2BBean> query = session.createQuery("from C2BBean where sid = ?1");
    query.setParameter(1, sid);
    List<C2BBean> qbList = query.getResultList();
    session.close();
    return qbList;

  }
}
