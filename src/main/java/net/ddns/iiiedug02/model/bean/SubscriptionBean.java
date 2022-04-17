package net.ddns.iiiedug02.model.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Setter
@Getter
@Table(name = "subscription")
public class SubscriptionBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
}
