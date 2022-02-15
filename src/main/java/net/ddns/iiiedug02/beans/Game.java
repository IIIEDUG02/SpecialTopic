package net.ddns.iiiedug02.beans;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Game")
public class Game {

  @Id
  @Column(name = "gameid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int gameid;

  @Column(name = "gamename")
  private String gamename;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "GameRole", joinColumns = {@JoinColumn(name = "gameid")},
      inverseJoinColumns = {@JoinColumn(name = "roleid")})
  private Set<Role> roles = new HashSet<Role>(0);

  public Game() {
    // TODO Auto-generated constructor stub
  }

  public int getGameid() {
    return gameid;
  }

  public void setGameid(int gameid) {
    this.gameid = gameid;
  }

  public String getGamename() {
    return gamename;
  }

  public void setGamename(String gamename) {
    this.gamename = gamename;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }



}
