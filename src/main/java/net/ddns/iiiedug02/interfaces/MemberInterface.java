package net.ddns.iiiedug02.interfaces;

import java.util.List;
import net.ddns.iiiedug02.model.beans.MemberBean;

/**
 * 統一MemberDao及MemberService介面
 */
public interface MemberInterface {
  public boolean addMember(MemberBean targetBean);

  public MemberBean selectByUsername(String username);

  public List<MemberBean> selectAll();

  public boolean delete(String username);

  public boolean updateMember(MemberBean targetBean);
}
