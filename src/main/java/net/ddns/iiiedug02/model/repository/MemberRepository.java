package net.ddns.iiiedug02.model.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.ddns.iiiedug02.model.bean.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
  public Optional<Member> findByUsername(String username);

  public Optional<Member> findByUid(int uid);

}
