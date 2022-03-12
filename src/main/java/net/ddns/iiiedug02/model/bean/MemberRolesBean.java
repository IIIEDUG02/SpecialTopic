package net.ddns.iiiedug02.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "member_roles")
public class MemberRolesBean implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @GenericGenerator(name = "generator", strategy = "foreign",
      parameters = @Parameter(name = "property", value = "member"))
  @Column(name = "uid", insertable = false, updatable = false)
  @GeneratedValue(generator = "generator")
  private int uid;

  @Column(name = "role")
  private String role;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "uid", referencedColumnName = "uid")
  private MemberBean member;
}
