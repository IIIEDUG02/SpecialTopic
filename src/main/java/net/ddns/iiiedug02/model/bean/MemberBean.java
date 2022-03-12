package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * JavaBean物件，對應資料庫中的members資料表
 */
@Entity
@Table(name = "members")
@Setter
@Getter
public class MemberBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "uid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int uid;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "activated")
  private short activated = 0;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "member",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private MemberDetailBean memberDetail;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "member",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private Set<MemberRolesBean> roles;

}
