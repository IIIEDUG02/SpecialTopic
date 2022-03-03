package net.ddns.iiiedug02.model.interfaces.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.ddns.iiiedug02.model.bean.demo.MemberBean;

public interface MemberRepository extends JpaRepository<MemberBean, Integer> {
  @Query(value = "from MemberBean where username = ?1")
  public MemberBean findByUsername(String username);

  @Query(value = "select * from MemberBean", nativeQuery = true)
  public List<MemberBean> findAll();
}
