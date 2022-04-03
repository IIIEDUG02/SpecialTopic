package net.ddns.iiiedug02.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ddns.iiiedug02.model.bean.CertificationBean;
import net.ddns.iiiedug02.model.repository.CertificationRepository;

@Service
public class CertificationService {

    @Autowired
    private CertificationRepository certRepository;

    public List<CertificationBean> findAll() {
        return certRepository.findAll();
    }

    public CertificationBean findByCertId(int certId) {
        Optional<CertificationBean> certBean = certRepository.findById(certId);
        if (!certBean.isEmpty()) {
            return certBean.get();
        }
        return null;
    }

    public void save(CertificationBean certBean) {
        certRepository.save(certBean);
    }


}
