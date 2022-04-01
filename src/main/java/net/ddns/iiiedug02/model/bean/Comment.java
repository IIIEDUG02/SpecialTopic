package net.ddns.iiiedug02.model.bean;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotBlank
  private String title;
  
  @NotBlank
  private String content;
  
  @NotBlank
  private String uuid;
  
  // 自我關聯
  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Comment parentComment;
  
  @OneToMany(mappedBy = "parentComment")
  private Set<Comment> comments;
  
  @ManyToOne
  @JoinColumn(name = "members_uid")
  private Member member;
  
  @ManyToOne
  @JoinColumn(name = "class_cid")
  private ClassBean classBean;
  
  @CreationTimestamp
  @Column(name = "post_time", nullable = false, updatable = false)
  private Date postTime;
  
  @UpdateTimestamp
  @Column(name = "edit_time")
  private Date editTime;
}
