package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.Data;

@Entity
@Table(name = "MPTEACHER")
@Component
public @Data class MPteacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHERID", referencedColumnName = "uid")
    private MemberInformation memberInformation;

    @Column(name = "MONTH")
    private int month;

    @Column(name = "MONTHAMOUNT")
    private int monthAmount;

}
