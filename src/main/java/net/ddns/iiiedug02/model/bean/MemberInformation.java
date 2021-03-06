package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * JavaBean物件，對應資料庫中的memberDetail資料表
 */
@IdClass(Member.class)
@Entity
@Table(name = "member_details")
@Setter
@Getter
public class MemberInformation implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    @JsonIgnore
    private Member member;

    @Column(name = "address")
    private String address = "";
    @Column(name = "phone")
    private String phone = "";
    @Column(name = "fullname")
    private String fullname = "";
    @Column(name = "email")
    private String email = "";
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "job")
    private String job = "";
    @Column(name = "photo")
    private String photo;
    @Column(name = "identitycard")
    private String identitycard;
    @Column(name = "gender")
    private String gender;
}
