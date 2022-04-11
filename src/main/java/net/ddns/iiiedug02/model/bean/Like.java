package net.ddns.iiiedug02.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "like_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long id;
  
  @Column(name = "comment_id")
  private Long commentId;
  
  @Column(name = "members_uid")
  private int membersUid;
  
  private int liked;
}
