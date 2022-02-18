package net.ddns.iiiedug02.model.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.daos.MemberDAO;

@Service("memberService")
public class MemberService {

  @Autowired
  private MemberDAO mdao;


  public MemberBean selectByUsername(String username) {
    MemberBean queryBean = mdao.selectByUsername(username);
    return queryBean;
  }

  public List<MemberBean> selectAll() {
    List<MemberBean> all = mdao.selectAll();
    return all;
  }

  public boolean delete(String username) {
    return mdao.delete(username);
  }

  public boolean updatePassword(String username, String password) {
    return mdao.updatePassword(username, password);
  }

  public boolean addMember(MemberBean targetdBean) {
    boolean result = mdao.addMember(targetdBean);
    System.out.println("Service:成功新增使用者");
    return result;
  }
}
