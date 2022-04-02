package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Table(name = "certification_name")
@Setter
@Getter
public class CertificationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int certId;

    private String certName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "certificationBean",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<CertificationClasses> certificationclasses;

}
