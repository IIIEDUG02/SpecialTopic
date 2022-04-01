package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.ddns.iiiedug02.model.bean.Comment;

// 能夠分頁及排序的 Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
  @Query("FROM Comment WHERE ClassBean.cid = :cid AND type = :type")
  List<Comment> getCommentsByClassCidAndCommentType(int cid, String type);
  List<Comment> findByUuid(String uuid);
  long deleteByUuid(String uuid);
}
