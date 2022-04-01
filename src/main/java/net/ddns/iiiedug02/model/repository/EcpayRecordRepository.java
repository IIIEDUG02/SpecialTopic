package net.ddns.iiiedug02.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.ddns.iiiedug02.model.bean.EcpayRecord;

@Repository
public interface EcpayRecordRepository extends JpaRepository<EcpayRecord, String> {

}
