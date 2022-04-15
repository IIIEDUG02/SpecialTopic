package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
  public List<Like> findByCommentIdAndMembersUid(Long commentId, int membersUid);
  public boolean existsByCommentIdAndMembersUid(Long id, int uid);
  
  @Query(value = "SELECT count(*) FROM like_table WHERE comment_id = ?1 AND liked = ?2", nativeQuery = true)
  public int countComments(Long commentId, int liked);
  
  @Modifying
  @Query("UPDATE Like SET liked = :liked WHERE comment_id = :commentId AND members_uid = :membersUid")
  public void updateByCommentIdAndMembersUid(int liked, Long commentId, int membersUid);
}
