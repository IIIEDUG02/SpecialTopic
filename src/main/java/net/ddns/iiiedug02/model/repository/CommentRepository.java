package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  @Query(value = "SELECT * FROM comment WHERE timestamp < ?1 AND class_cid = ?2 AND type = ?3 AND parent_id is null",
      countQuery = "SELECT count(*) from comment WHERE timestamp < ?1 AND class_cid = ?2 AND type = ?3 AND parent_id is null",
      nativeQuery = true)
  Page<Comment> getCommentsByClassCidAndCommentType(Long timestamp, int cid, String type, Pageable pageable);
  List<Comment> findByUuid(String uuid);
  long deleteByUuid(String uuid);
}