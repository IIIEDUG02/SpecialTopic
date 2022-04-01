package net.ddns.iiiedug02.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.ddns.iiiedug02.model.bean.Comment;
import net.ddns.iiiedug02.model.repository.CommentRepository;

@Service
public class CommentService {
  @Autowired
  private CommentRepository repository;

  Comment create(Comment comment) {
    return repository.save(comment);
  }
  
  Comment update(Comment comment) {
    return repository.save(comment);
  }
  
  
  /**
   * 根據留言的 uuid 來刪除指定的留言
   * 
   * @param uuid: 該則留言的 uuid
   * @return 受影響的筆數 
   */
  long deleteByUuid(String uuid) {
    return repository.deleteByUuid(uuid);
  }
  
  List<Comment> getAllComments(int pageNum, int pageSize) {
    Pageable pages = PageRequest.of(pageNum, pageSize, Direction.DESC, "post_time");

    return repository.findAll(pages).getContent();
  }

  /**
   * 根據課程的 cid 與留言的類型(type)來取得該課程的所有留言
   * 
   * @param cid: 課程的 cid
   * @param type: 留言的類型
   * @return 
   */
  List<Comment> getCommentsByCidAndCommentType(int cid, String type) {
    return repository.getCommentsByClassCidAndCommentType(cid, type);
  }

  Comment getCommentByUuid(String uuid) {
    return repository.findByUuid(uuid).get(0);
  }
}
