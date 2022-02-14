package net.ddns.iiiedug02.services;

import java.util.List;
import org.hibernate.Session;
import net.ddns.iiiedug02.beans.MemberBean;
import net.ddns.iiiedug02.daos.MemberDAO;

public class MemberService {
  MemberDAO mdao;

  public MemberService() {}

  public MemberService(Session session) {
    mdao = new MemberDAO(session);
  }

  public MemberBean selectByUsername(String username) {
    return mdao.selectByUsername(username);
  }

  public List<MemberBean> selectAll() {
    return mdao.selectAll();
  }

  public boolean delete(String username) {
    return mdao.delete(username);
  }

  public boolean updatePassword(String username, String password) {
    return mdao.updatePassword(username, password);
  }

  public boolean addMember(MemberBean targetBean) {
    return mdao.addMember(targetBean);
  }
}
