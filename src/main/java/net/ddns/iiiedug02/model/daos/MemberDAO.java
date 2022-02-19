package net.ddns.iiiedug02.model.daos;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import net.ddns.iiiedug02.interfaces.IMemberDAO;
import net.ddns.iiiedug02.model.beans.MemberBean;

@Repository("memberDAO")
public class MemberDAO implements IMemberDAO {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public MemberBean selectByUsername(String username) {
    Session session = sessionFactory.getCurrentSession();
    Query<MemberBean> query = session.createQuery("from MemberBean where username = ?1");
    query.setParameter(1, username);
    MemberBean resultBean = (MemberBean) query.uniqueResult();
    return resultBean;
  }

  @Override
  public List<MemberBean> selectAll() {
    Session session = sessionFactory.getCurrentSession();
    Query<MemberBean> all = session.createQuery("from MemberBean", MemberBean.class);
    return all.list();
  }

  @Override
  public boolean delete(String username) {
    Session session = sessionFactory.getCurrentSession();
    MemberBean target = selectByUsername(username);
    if (target != null) {
      session.delete(target);
      return true;
    }
    return false;
  }

  @Override
  public boolean addMember(MemberBean targetBean) {
    Session session = sessionFactory.getCurrentSession();
    session.save(targetBean);
    return true;
  }

  @Override
  public boolean updateMember(MemberBean targetBean) {
    Session session = sessionFactory.getCurrentSession();
    session.update(targetBean);
    return false;
  }
}
