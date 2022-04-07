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
   * @param cid: 課程的 cid
   * @param type: 留言的類型
   * @return 
   */
  public List<Comment> getCommentsByCidAndCommentType(int cid, String type, int pageNum, int pageSize) {
    Pageable pageRequest = PageRequest.of(pageNum, pageSize, Direction.DESC, "postTime");
    
    return repository.getCommentsByClassCidAndCommentType(cid, type, pageRequest);
  }

  public Comment getCommentByUuid(String uuid) {
    List<Comment> comments = repository.findByUuid(uuid);
    
    return comments.size() == 1 ? comments.get(0): null;
  }
}
