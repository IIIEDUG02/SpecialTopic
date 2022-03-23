package net.ddns.iiiedug02.model.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberInformation;

@Repository
public interface MemberInformationRepository extends JpaRepository<MemberInformation, Integer> {
  

  public Optional<MemberInformation> findByUid(int uid);

}
