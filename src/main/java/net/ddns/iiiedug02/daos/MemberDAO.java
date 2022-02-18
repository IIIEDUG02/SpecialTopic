package net.ddns.iiiedug02.daos;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import net.ddns.iiiedug02.beans.MemberBean;
import net.ddns.iiiedug02.interfaces.IMemberDAO;

@Repository("memberDAO")
public class MemberDAO implements IMemberDAO {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public MemberBean selectByUsername(String username) {
    Session session = sessionFactory.getCurrentSession();
    return session.load(MemberBean.class, username);
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
    MemberBean target = session.get(MemberBean.class, username);
    if (target != null) {
      session.delete(target);
      return true;
    }
    return false;
  }

  @Override
  public boolean updatePassword(String username, String password) {
    Session session = sessionFactory.getCurrentSession();
    MemberBean target = session.get(MemberBean.class, username);
    if (target != null) {
      target.setPassword(password);
      session.update(target);
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
}
