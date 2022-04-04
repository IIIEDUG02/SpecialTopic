package net.ddns.iiiedug02.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ddns.iiiedug02.model.bean.ProgressRecord;
import net.ddns.iiiedug02.model.repository.ProgressRecordReposity;

@Service
public class ProgressRecordService {

    @Autowired
    private ProgressRecordReposity progressRecordReposity;

    public ProgressRecord findByUidAndCuid(int uid, int cuid) {
        return progressRecordReposity.findByUidAndCuid(uid, cuid);
    }

    public void save(ProgressRecord pr) {
        progressRecordReposity.save(pr);
    }


}
