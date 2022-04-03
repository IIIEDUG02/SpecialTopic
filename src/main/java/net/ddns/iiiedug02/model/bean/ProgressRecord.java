package net.ddns.iiiedug02.model.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Table(name = "progress_record")
@Setter
@Getter
public class ProgressRecord {

    @Id
    private int id;

    private int uid;

    private int cuid;

    private float maxTimePoint;

    private float timeSum;

    private short status;

}
