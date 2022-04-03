package net.ddns.iiiedug02.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.ddns.iiiedug02.model.bean.ProgressRecord;

public interface ProgressRecordReposity extends JpaRepository<ProgressRecord, Integer> {

    public ProgressRecord findByUidAndCuid(int uid, int cuid);

}
