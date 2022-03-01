package net.ddns.iiiedug02.model.daos;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.beans.MemberBean;

/**
 * DAO物件，針對MemberBean對資料庫做增刪改查
 */
@Repository("memberDAO")
@Transactional
public class MemberDAO {

  @Autowired
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  @Transactional(readOnly = true)
  public MemberBean selectByUsername(String username) {
    Session session = sessionFactory.openSession();
    Query<MemberBean> query = session.createQuery("from MemberBean where username = ?1");
    query.setParameter(1, username);
    MemberBean qmb = query.uniqueResult();
    session.close();
    return qmb;
  }

  @Transactional(readOnly = true)
  public List<MemberBean> selectAll() {
    Session session = sessionFactory.openSession();
    Query<MemberBean> all = session.createQuery("from MemberBean", MemberBean.class);
    session.close();
    return all.list();
  }

  public boolean deleteByName(String username) {
    Session session = sessionFactory.openSession();
    MemberBean target = selectByUsername(username);
    session.delete(target);
    session.close();
    return true;
  }

  public Serializable addMember(MemberBean targetBean) {
    Session session = sessionFactory.openSession();
    Serializable s = session.save(targetBean);
    session.close();
    return s;
  }

  public boolean updateMember(MemberBean targetBean) {
    Session session = sessionFactory.getCurrentSession();
    session.update(targetBean);
    session.close();
    return true;
  }

  @Transactional(readOnly = true)
  public MemberBean selectByToken(String token) {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    @SuppressWarnings("unchecked")
    Query<MemberBean> query = (session.createQuery("from MemberBean where token = ?1"));
    query.setParameter(1, token);
    MemberBean qmb = query.uniqueResult();
    session.getTransaction().commit();
    session.close();
    return qmb;
  }
}
