package net.ddns.iiiedug02.model.bean;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Anna
 * 
 * 留言表的實體(Entity)，此表會自我關聯(self-referencing)。
 * 每則留言都會有父子關係，父留言為 parentComment，子留言(們)為 comments。
 * 
 * 如果沒使用 @EqualsAndHashCode，關聯會造成無窮迴圈，
 * 有其他解法能解決此問題，但直接加上這個註釋是最快的方式。
 * 
 */
@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long id;
  
  @NotBlank(message = "留言標題不可空白！")
  private String title;
  
  @NotBlank(message = "留言內容不可空白！")
  private String content;
  
  private String uuid;
  
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String type;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "parent_id", referencedColumnName = "id")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Comment parentComment;
  
  @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Set<Comment> comments;
  
  @ManyToOne
  @JoinColumn(name = "members_uid")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Member member;
  
  @ManyToOne
  @JoinColumn(name = "class_cid")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private ClassBean classBean;
  
  @CreationTimestamp
  @Column(name = "post_time", nullable = false, updatable = false)
  private LocalDateTime postTime;
  
  @Column(name = "edit_time")
  private LocalDateTime editTime;
}
