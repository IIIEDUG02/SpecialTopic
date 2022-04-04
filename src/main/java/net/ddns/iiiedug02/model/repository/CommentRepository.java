package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  @Query("FROM Comment WHERE classBean.cid = :cid AND type = :type AND parentComment = NULL")
  List<Comment> getCommentsByClassCidAndCommentType(int cid, String type, Pageable pageable);
  List<Comment> findByUuid(String uuid);
  long deleteByUuid(String uuid);
}
