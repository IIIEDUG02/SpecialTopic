package net.ddns.iiiedug02.model.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ddns.iiiedug02.interfaces.MemberInterface;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.daos.MemberDAO;

/**
 * Service物件，將完成指定商業邏輯的MemberBean物件，呼叫MemberDao執行增刪改查
 */
@Service("memberService")
public class MemberService implements MemberInterface {

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

  public boolean updateMember(MemberBean targetdBean) {
    return mdao.updateMember(targetdBean);
  }

  public boolean addMember(MemberBean targetdBean) {
    if (targetdBean.getAuth() == null) {
      targetdBean.setAuth("normal");
    }

    MemberBean queryBean = mdao.selectByUsername(targetdBean.getUsername());
    boolean result;

    if (queryBean == null) {
      result = mdao.addMember(targetdBean);
    } else {
      result = false;
    }
    return result;
  }
}
