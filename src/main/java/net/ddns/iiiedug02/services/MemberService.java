package net.ddns.iiiedug02.services;

import java.util.List;
import org.hibernate.Session;
import net.ddns.iiiedug02.beans.MemberBean;
import net.ddns.iiiedug02.daos.MemberDAO;
import net.ddns.iiiedug02.utils.JasyptUtil;

public class MemberService {
  MemberDAO mdao;
  JasyptUtil encryptorUtil = new JasyptUtil();

  public MemberService() {}

  public MemberService(Session session) {
    mdao = new MemberDAO(session);
  }

  public MemberBean selectByUsername(String username) {
    MemberBean encryptedBean = mdao.selectByUsername(username);
    MemberBean decryptedBean = new MemberBean();
    if (null == encryptedBean) {
      decryptedBean.setUsername("");
      decryptedBean.setPassword("");
    } else {
      decryptedBean.setUsername(encryptedBean.getUsername());
      decryptedBean.setPassword(encryptorUtil.decrypt(encryptedBean.getPassword()));
    }
    return decryptedBean;
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

  public boolean addMember(MemberBean decryptedBean) {
    MemberBean encryptedBean = new MemberBean();
    encryptedBean.setUsername(decryptedBean.getUsername());
    String encryptPassword = encryptorUtil.encrypt(decryptedBean.getPassword());
    encryptedBean.setPassword(encryptPassword);
    return mdao.addMember(encryptedBean);
  }
}
