package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Table(name = "certification_calsses")
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CertificationClasses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certId", referencedColumnName = "certId")
    @JsonIgnore
    private CertificationBean certificationBean;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid", referencedColumnName = "cid")
    private ClassBean classbean;

}
