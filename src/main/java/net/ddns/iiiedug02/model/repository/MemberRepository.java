package net.ddns.iiiedug02.model.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.ddns.iiiedug02.model.bean.MemberBean;

@Repository
public interface MemberRepository extends JpaRepository<MemberBean, Integer> {
  public Optional<MemberBean> findByUsername(String username);
}
