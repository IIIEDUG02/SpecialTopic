package net.ddns.iiiedug02.model.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.beans.MemberBean;
import net.ddns.iiiedug02.model.daos.MemberDAO;

/**
 * Service物件，將完成指定商業邏輯的MemberBean物件，呼叫MemberDao執行增刪改查
 */
@Service("memberService")
@Transactional
public class MemberService {

  @Autowired
  private MemberDAO mdao;

  public MemberBean selectByUsername(String username) {
    MemberBean queryBean = mdao.selectByUsername(username);
    return queryBean;
  }

  public MemberBean selectByToken(String token) {
    MemberBean queryBean = mdao.selectByToken(token);
    return queryBean;
  }

  public List<MemberBean> selectAll() {
    List<MemberBean> all = mdao.selectAll();
    return all;
  }

  public boolean deleteByName(String username) {
    return mdao.deleteByName(username);
  }

  public boolean updateMember(MemberBean targetdBean) {
    return mdao.updateMember(targetdBean);
  }

  public boolean addMember(MemberBean targetdBean) {
    MemberBean queryBean = mdao.selectByUsername(targetdBean.getUsername());

    if (queryBean != null)
      return false;

    if (targetdBean.getRoles() == null) {
      targetdBean.setRoles("normal");
    }

    return mdao.addMember(targetdBean) != null;
  }
}
