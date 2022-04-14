package net.ddns.iiiedug02.model.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Component
@Table(name = "suggest")
public class Suggest {
    @Id
    private int id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private short status;
}
