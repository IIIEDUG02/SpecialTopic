package net.ddns.iiiedug02.model.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ddns.iiiedug02.model.bean.EcpayRecord;
import net.ddns.iiiedug02.model.repository.EcpayRecordRepository;

@Service
/*
 * 綠界交易紀錄的Service
 * 
 * @author Nilm
 */
public class EcpayRecordService {

    @Autowired
    private EcpayRecordRepository ecpayRecordRepository;

    public void save(EcpayRecord ecpayRecord) {
        ecpayRecordRepository.save(ecpayRecord);
    }

    public EcpayRecord findByOrderId(String orderId) {
        Optional<EcpayRecord> ec = ecpayRecordRepository.findById(orderId);
        if (ec.isEmpty()) {
            return null;
        }
        return ec.get();
    }

}
