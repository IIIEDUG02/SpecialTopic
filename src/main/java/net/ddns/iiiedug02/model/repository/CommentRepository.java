package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  @Query(value = "SELECT * FROM comment WHERE parent_id is null AND class_cid = ?1 AND type = ?2",
      countQuery = "SELECT count(*) from comment WHERE parent_id is null AND class_cid = ?1 AND type = ?2",
      nativeQuery = true)
  Page<Comment> getCommentsByClassCidAndCommentType(int cid, String type, Pageable pageable);
  List<Comment> findByUuid(String uuid);
  long deleteByUuid(String uuid);
}