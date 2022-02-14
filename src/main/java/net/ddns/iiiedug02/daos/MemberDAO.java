package net.ddns.iiiedug02.daos;

import java.util.List;
import org.hibernate.Session;
import net.ddns.iiiedug02.beans.MemberBean;
import net.ddns.iiiedug02.interfaces.IMemberDAO;

public class MemberDAO implements IMemberDAO {

  private Session session;

  public MemberDAO() {}

  public MemberDAO(Session session) {
    this.session = session;
  }

  @Override
  public MemberBean selectByUsername(String username) {
    return session.get(MemberBean.class, username);
  }

  @Override
  public List<MemberBean> selectAll() {
    return session.createQuery("from MemberBean", MemberBean.class).list();
  }

  @Override
  public boolean delete(String username) {
    MemberBean target = session.get(MemberBean.class, username);
    if (target != null) {
      session.delete(target);
      return true;
    }
    return false;
  }

  @Override
  public boolean updatePassword(String username, String password) {
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
    MemberBean queryBean = session.get(MemberBean.class, targetBean.getUsername());
    if (queryBean == null) {
      session.save(targetBean);
      return true;
    }
    return false;
  }
}
