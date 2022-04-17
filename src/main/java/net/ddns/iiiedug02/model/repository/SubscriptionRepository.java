package net.ddns.iiiedug02.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.ddns.iiiedug02.model.bean.SubscriptionBean;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionBean, Integer> {

}
