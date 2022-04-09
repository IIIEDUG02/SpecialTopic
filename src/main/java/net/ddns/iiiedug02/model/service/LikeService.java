package net.ddns.iiiedug02.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ddns.iiiedug02.model.bean.Like;
import net.ddns.iiiedug02.model.repository.LikeRepository;

@Service
public class LikeService {
  @Autowired
  private LikeRepository repository;
  
  public List<Like> findByCommentIdAndMembersUid(Long commentId, int membersUid) {
    return repository.findByCommentIdAndMembersUid(commentId, membersUid);
  }
  public Like create(Like like) {
    return repository.save(like); 
  }
  
  public int countByCommentId(Long id) {
    return repository.countComments(id, 1);
  }
  
  public boolean existsByCommentIdAndMembersUid(Long id, int uid) {
    return repository.existsByCommentIdAndMembersUid(id, uid);
  }
  
  @Transactional
  public void updateByCommentIdAndMembersUid(int liked, Long commentId, int membersUid) {
    repository.updateByCommentIdAndMembersUid(liked, commentId, membersUid);
  }
}
