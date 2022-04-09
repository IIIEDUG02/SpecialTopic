package net.ddns.iiiedug02.model.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  public Comment create(Comment comment) {
    return repository.save(comment);
  }
  
  public Comment update(Comment comment) {
    return repository.save(comment);
  }
  
  
  /**
   * 根據留言的 uuid 來刪除指定的留言
   * 
   * @param uuid: 該則留言的 uuid
   * @return 受影響的筆數 
   */
  public long deleteByUuid(String uuid) {
    return repository.deleteByUuid(uuid);
  }
  
  /**
   * 根據課程的 cid 與留言的類型(type)來取得該課程的所有留言
   * 
   * @param timestamp: Created Before Timestamp
   * @param cid: 課程的 cid
   * @param type: 留言的類型
   * @param pageSize: 每頁的資料筆數
   * @return 
   */
  public Page<Comment> getCommentsByCidAndCommentType(Long timestamp, int cid, String type, int pageSize) {
    // 在這裡，每次都指定第零頁，目的是為了能夠告訴前端是否還有資料可以再被加載
    Pageable pageRequest = PageRequest.of(0, pageSize, Direction.DESC, "post_time");
    return repository.getCommentsByClassCidAndCommentType(timestamp, cid, type, pageRequest);
  }

  public Comment getCommentByUuid(String uuid) {
    List<Comment> comments = repository.findByUuid(uuid);
    
    return comments.size() == 1 ? comments.get(0): null;
  }
  
  @Transactional
  public void updateContentById(String content, Long id) {
    repository.updateContentById(content, LocalDateTime.now(), id);
  }
}
