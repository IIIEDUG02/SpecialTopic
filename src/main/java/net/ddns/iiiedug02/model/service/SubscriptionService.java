package net.ddns.iiiedug02.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ddns.iiiedug02.model.bean.SubscriptionBean;
import net.ddns.iiiedug02.model.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<SubscriptionBean> findAll() {
        return subscriptionRepository.findAll();
    }

    public void save(SubscriptionBean sb) {
        subscriptionRepository.save(sb);
    }
}
